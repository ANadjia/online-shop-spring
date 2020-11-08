package com.esisba.webservice.entitiy;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String email;

    private String first_name;

    private String last_name;

    private String password;

    private int active;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public User() {
    }

    public User(Long id, String email, String first_name, String last_name, String password, int active) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.active = active;
    }
}
