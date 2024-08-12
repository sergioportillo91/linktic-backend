package com.reservation.api.services;

import com.reservation.api.entities.CustomerEntity;
import com.reservation.api.entities.UserEntity;
import com.reservation.api.exceptions.CustomNotFoundException;
import com.reservation.api.mappers.CustomerMapper;
import com.reservation.api.mappers.UserMapper;
import com.reservation.api.models.Customer;
import com.reservation.api.models.PageModel;
import com.reservation.api.repositories.CustomerRepository;
import com.reservation.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final CustomerMapper mapper;

    private final UserMapper userMapper;

    public PageModel<List<Customer>> filter(Customer request, Integer rowsPerPage, Integer skip)  {
        int pageNumber = (int) Math.ceil((double) skip / rowsPerPage);
        Pageable pageable = (rowsPerPage > 0) ? PageRequest.of(pageNumber, rowsPerPage) : Pageable.unpaged();
        Page<CustomerEntity> page = this.customerRepository.filter(request.getId(),request.getFirstName(),request.getLastName(), request.getIdentification(), pageable);
        return new PageModel<>(this.mapper.toModel(page.getContent()), page.getTotalElements());
    }

    public void save(Customer request)  {
        final CustomerEntity customerEntity = mapper.toEntity(request);
        UserEntity userEntity = this.userRepository.save(userMapper.toEntity(request.getUser()));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        customerEntity.setUser(userEntity);
        this.customerRepository.save(customerEntity);
    }

    public void update(Long customerId, Customer request) {
        CustomerEntity existingCustomer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomNotFoundException("id","El id del cliente no existe: " + customerId));

        existingCustomer.setFirstName(request.getFirstName().toUpperCase().trim());
        existingCustomer.setLastName(request.getLastName().toUpperCase().trim());
        existingCustomer.setEmail(request.getEmail().toUpperCase().trim());
        existingCustomer.setPhone(request.getPhone().trim());
        existingCustomer.setIdentification(request.getIdentification().trim());

        this.customerRepository.save(existingCustomer);
    }


    public void delete(Long customerId) {
        this.customerRepository.deleteById(customerId);
    }

}
