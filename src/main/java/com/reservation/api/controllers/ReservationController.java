package com.reservation.api.controllers;

import com.reservation.api.mappers.ReservationMapper;
import com.reservation.api.models.PageModel;
import com.reservation.api.models.Reservation;
import com.reservation.api.request.ReservationFilterRequest;
import com.reservation.api.request.ReservationRequest;
import com.reservation.api.response.PageResponse;
import com.reservation.api.response.ReservationResponse;

import com.reservation.api.services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${request-mapping.controller.reservation}")
public class ReservationController {

    private final ReservationService reservationService;

    private final ReservationMapper mapper;

    @GetMapping
    public ResponseEntity<PageResponse<List<ReservationResponse>>> filter(ReservationFilterRequest request, Integer rowsPerPage, Integer skip)  {
        PageModel<List<Reservation>> response = this.reservationService.filter(this.mapper.requestToModelFilter(request), rowsPerPage, skip);
        return new ResponseEntity<>(new PageResponse<>(this.mapper.toResponse(response.data()), response.total()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ReservationRequest request) {
        this.reservationService.save(this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ReservationRequest request) {
        this.reservationService.update(id, this.mapper.requestToModel(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        this.reservationService.cancelReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
