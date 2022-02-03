package com.mettle.userfeaturemanager.model.datastore;

import com.mettle.userfeaturemanager.model.FeatureScope;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Feature {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean status;
    private FeatureScope scope;
}
