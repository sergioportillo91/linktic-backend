package com.reservation.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="RESERVATION")
@SequenceGenerator(name = "SEQ_RESERVATION", sequenceName = "SEQ_RESERVATION", initialValue = 1, allocationSize = 1)
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESERVATION")
    @Column(name = "ID", columnDefinition = "NUMBER(10)", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "SERVICE_ID", nullable = false)
    private ServiceEntity service;

    @Column(name = "RESERVATION_DATE")
    private Date reservationDate;

    @Column(name = "STAR_DATE")
    private Date starDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "STATE", columnDefinition = "VARCHAR2(15)",length = 15, nullable = false)
    private String state;

}
