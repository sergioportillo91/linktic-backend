package com.reservation.api.mappers;

import com.reservation.api.entities.ReservationEntity;
import com.reservation.api.models.Reservation;
import com.reservation.api.request.ReservationFilterRequest;
import com.reservation.api.request.ReservationRequest;
import com.reservation.api.response.ReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationEntity toEntity(Reservation model);

    ReservationResponse toResponse(Reservation model);

    Reservation requestToModel(ReservationRequest request);

    @Mapping(source = "customer", target = "customer.id")
    @Mapping(source = "service", target = "service.id")
    Reservation requestToModelFilter(ReservationFilterRequest request);

    Reservation toModel(ReservationEntity entity);

    List<ReservationEntity> toEntity(List<Reservation> model);

    List<Reservation> toModel(List<ReservationEntity> entity);

    List<ReservationResponse> toResponse(List<Reservation> request);
}
