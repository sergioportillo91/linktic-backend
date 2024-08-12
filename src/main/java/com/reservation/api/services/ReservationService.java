package com.reservation.api.services;

import com.reservation.api.entities.ReservationEntity;
import com.reservation.api.exceptions.CustomNotFoundException;
import com.reservation.api.mappers.CustomerMapper;
import com.reservation.api.mappers.ReservationMapper;
import com.reservation.api.mappers.ServiceMapper;
import com.reservation.api.models.PageModel;
import com.reservation.api.models.Reservation;
import com.reservation.api.repositories.CustomerRepository;
import com.reservation.api.repositories.ReservationRepository;
import com.reservation.api.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final CustomerRepository customerRepository;

    private final ServiceRepository serviceRepository;

    private final CustomerMapper customerMapper;

    private final ReservationMapper mapper;

    private final ServiceMapper serviceMapper;


    public PageModel<List<Reservation>> filter(Reservation request, Integer rowsPerPage, Integer skip)  {
        int pageNumber = (int) Math.ceil((double) skip / rowsPerPage);
        Pageable pageable = (rowsPerPage > 0) ? PageRequest.of(pageNumber, rowsPerPage) : Pageable.unpaged();

        Long serviceId = null;
        Long customerId = null;

        if(request.getService() != null){
            serviceId = request.getService().getId();
        }
        if(request.getCustomer() != null){
            customerId = request.getCustomer().getId();
        }

        Page<ReservationEntity> page = this.reservationRepository.filter(serviceId, customerId,request.getReservationDate(), pageable);
        return new PageModel<>(this.mapper.toModel(page.getContent()), page.getTotalElements());
    }

    public void save(Reservation request)  {

        serviceRepository.findById(request.getService().getId())
                .orElseThrow(() -> new CustomNotFoundException("id","El service con ese id no existe: " + request.getService().getId()));

        customerRepository.findById(request.getCustomer().getId())
                .orElseThrow(() -> new CustomNotFoundException("id","El customer con ese id no existe: " + request.getCustomer().getId()));
        this.reservationRepository.save(mapper.toEntity(request));
    }

    public void update(Long reservationId, Reservation request) {
        ReservationEntity existingReservation = this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomNotFoundException("id","La reservacion con ese id no existe: " + reservationId));

        existingReservation.setCustomer(this.customerMapper.toEntity(request.getCustomer()));
        existingReservation.setService(this.serviceMapper.toEntity(request.getService()));
        existingReservation.setReservationDate(request.getReservationDate());
        existingReservation.setStarDate(request.getStarDate());
        existingReservation.setEndDate(request.getEndDate());
        existingReservation.setState(request.getState());

        this.reservationRepository.save(existingReservation);
    }


    public void delete(Long reservationId) {

        this.reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomNotFoundException("id", "La reservacion con ese id no existe: " + reservationId));

        this.reservationRepository.deleteById(reservationId);
    }

    @Transactional
    public void cancelReservation(Long id) {
        this.reservationRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("id", "La reservacion con ese id no existe: " + id));

        this.reservationRepository.cancelReservation(id);
    }
}
