package com.reservation.api.mappers;

import com.reservation.api.entities.CustomerEntity;
import com.reservation.api.models.Customer;
import com.reservation.api.request.CustomerRequest;
import com.reservation.api.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity toEntity(Customer model);

    Customer requestToModel(CustomerRequest request);

    Customer toModel(CustomerEntity entity);

    List<CustomerEntity> toEntity(List<Customer> model);

    List<Customer> toModel(List<CustomerEntity> entity);

    List<CustomerResponse> toResponse(List<Customer> request);
}
