package com.reservation.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationFilterRequest {

    private Long customer;

    private Long service;

    private Date reservationDate;

}
