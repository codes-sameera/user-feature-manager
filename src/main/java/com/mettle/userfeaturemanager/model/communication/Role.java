package com.mettle.userfeaturemanager.model.communication;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Long id;
    private String name;
    private List<Feature> features;
}
