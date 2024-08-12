package com.reservation.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    private Long id;

    private String identification;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private User user;
}
