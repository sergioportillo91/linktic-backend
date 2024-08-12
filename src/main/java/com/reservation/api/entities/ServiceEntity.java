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
@Table(name="SERVICE")
@SequenceGenerator(name = "SEQ_SERVICE", sequenceName = "SEQ_SERVICE", initialValue = 1, allocationSize = 1)
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICE")
    @Column(name = "ID", columnDefinition = "NUMBER(10)", nullable = false)
    private Long id;

    @Column(name = "NAME", columnDefinition = "VARCHAR2(30)", length = 30, nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2(50)" , length = 50, nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;


}
