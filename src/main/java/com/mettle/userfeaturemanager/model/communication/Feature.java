package com.mettle.userfeaturemanager.model.communication;

import com.mettle.userfeaturemanager.model.FeatureScope;
import lombok.Data;

@Data
public class Feature {
    private Long id;
    private String name;
    private boolean status;
    private FeatureScope scope;
}
