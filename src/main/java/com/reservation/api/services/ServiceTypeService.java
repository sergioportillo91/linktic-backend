package com.reservation.api.services;


import com.reservation.api.entities.ServiceEntity;

import com.reservation.api.exceptions.CustomNotFoundException;
import com.reservation.api.mappers.ServiceMapper;
import com.reservation.api.models.PageModel;

import com.reservation.api.models.ServiceType;
import com.reservation.api.repositories.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {

    private final ServiceRepository serviceRepository;

    private final ServiceMapper mapper;

    public PageModel<List<ServiceType>> filter(ServiceType request, Integer rowsPerPage, Integer skip)  {
        int pageNumber = (int) Math.ceil((double) skip / rowsPerPage);
        Pageable pageable = (rowsPerPage > 0) ? PageRequest.of(pageNumber, rowsPerPage) : Pageable.unpaged();
        Page<ServiceEntity> page = this.serviceRepository.filter(request.getId(),request.getName(), pageable);
        return new PageModel<>(this.mapper.toModel(page.getContent()), page.getTotalElements());
    }

    public void save(ServiceType request)  {
        final ServiceEntity customerEntity = mapper.toEntity(request);
        this.serviceRepository.save(customerEntity);
    }

    public void delete(Long serviceTypeId) {
        this.serviceRepository.deleteById(serviceTypeId);
    }

    public void update(Long serviceTypeId, ServiceType request) {
        ServiceEntity existingService = this.serviceRepository.findById(serviceTypeId)
                .orElseThrow(() -> new CustomNotFoundException("id","El id del servicio no existe: " + serviceTypeId));

        existingService.setName(request.getName().toUpperCase().trim());
        existingService.setDescription(request.getDescription().trim());
        existingService.setPrice(request.getPrice());
        this.serviceRepository.save(existingService);
    }




}
