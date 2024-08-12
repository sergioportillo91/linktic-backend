package com.reservation.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    private Long id;

    private Customer customer;

    private ServiceType service;

    private Date reservationDate;

    private Date starDate;

    private Date endDate;

    private String state;
}
