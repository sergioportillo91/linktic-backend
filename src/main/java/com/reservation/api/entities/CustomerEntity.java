package com.reservation.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CUSTOMER")
@SequenceGenerator(name = "SEQ_CUSTOMER", sequenceName = "SEQ_CUSTOMER", initialValue = 1, allocationSize = 1)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER")
    @Column(name = "ID", columnDefinition = "NUMBER(10)", nullable = false)
    private Long id;

    @Column(name = "IDENTIFICATION", columnDefinition = "VARCHAR2(20)", length = 20, nullable = false)
    private String identification;

    @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR2(30)", length = 30, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(30)", length = 30, nullable = false)
    private String lastName;

    @Column(name = "EMAIL", columnDefinition = "VARCHAR2(40)", length = 20, nullable = false)
    private String email;

    @Column(name = "PHONE", columnDefinition = "VARCHAR2(10)", length = 10, nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;
}
