package com.reservation.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String identification;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private UserResponse user;
}
