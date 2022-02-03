package com.mettle.userfeaturemanager.model.datastore;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    @OneToOne
    private Role role;
    @OneToMany
    private List<Feature> features;
}
