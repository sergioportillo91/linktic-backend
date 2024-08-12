package com.reservation.api.mappers;

import com.reservation.api.entities.ServiceEntity;
import com.reservation.api.models.ServiceType;
import com.reservation.api.request.ServiceRequest;
import com.reservation.api.response.ServiceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceEntity toEntity(ServiceType model);

    ServiceType requestToModel(ServiceRequest request);

    ServiceResponse toResponse(ServiceType model);

    ServiceType toModel(ServiceEntity entity);

    List<ServiceEntity> toEntity(List<ServiceType> model);

    List<ServiceType> toModel(List<ServiceEntity> entity);

    List<ServiceResponse> toResponse(List<ServiceType> request);
}
