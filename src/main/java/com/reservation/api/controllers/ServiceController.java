package com.reservation.api.controllers;

import com.reservation.api.mappers.ServiceMapper;
import com.reservation.api.models.PageModel;
import com.reservation.api.models.ServiceType;
import com.reservation.api.request.ServiceRequest;
import com.reservation.api.response.PageResponse;
import com.reservation.api.response.ServiceResponse;
import com.reservation.api.services.ServiceTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.service}")
public class ServiceController {


    private final ServiceTypeService serviceTypeService;

    private final ServiceMapper mapper;

    @GetMapping
    public ResponseEntity<PageResponse<List<ServiceResponse>>> filter(ServiceRequest request, Integer rowsPerPage, Integer skip)  {
        PageModel<List<ServiceType>> response = this.serviceTypeService.filter(this.mapper.requestToModel(request), rowsPerPage, skip);
        return new ResponseEntity<>(new PageResponse<>(this.mapper.toResponse(response.data()), response.total()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ServiceRequest request) {
        this.serviceTypeService.save(this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.serviceTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ServiceRequest request) {
        this.serviceTypeService.update(id, this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
