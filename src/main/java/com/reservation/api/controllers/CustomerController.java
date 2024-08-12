package com.reservation.api.controllers;

import com.reservation.api.mappers.CustomerMapper;
import com.reservation.api.models.Customer;
import com.reservation.api.models.PageModel;
import com.reservation.api.request.CustomerRequest;
import com.reservation.api.response.CustomerResponse;
import com.reservation.api.response.PageResponse;
import com.reservation.api.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.customer}")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerMapper mapper;

    @GetMapping
    public ResponseEntity<PageResponse<List<CustomerResponse>>> filter(CustomerRequest request, Integer rowsPerPage, Integer skip)  {
        PageModel<List<Customer>> response = this.customerService.filter(this.mapper.requestToModel(request), rowsPerPage, skip);
        return new ResponseEntity<>(new PageResponse<>(this.mapper.toResponse(response.data()), response.total()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CustomerRequest request) {
        this.customerService.save(this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CustomerRequest request) {
        this.customerService.update(id, this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
