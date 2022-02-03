package com.mettle.userfeaturemanager.model.communication;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Role role;
    private List<Feature> features;
}
