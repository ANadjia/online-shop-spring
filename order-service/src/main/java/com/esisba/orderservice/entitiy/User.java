package com.esisba.orderservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String first_name;

    private String last_name;

    private String password;

    private int active;
}
