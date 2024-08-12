package com.reservation.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    private Long id;

    @NotNull
    private CustomerRequest customer;

    @NotNull
    private ServiceRequest service;

    @NotNull
    private Date reservationDate;

    @NotNull
    private Date starDate;

    @NotNull
    private Date endDate;

    @NotNull
    @NotBlank
    private String state;
}
