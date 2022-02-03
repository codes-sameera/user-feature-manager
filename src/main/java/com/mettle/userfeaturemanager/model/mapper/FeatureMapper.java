package com.mettle.userfeaturemanager.model.mapper;

import com.mettle.userfeaturemanager.model.communication.Feature;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureMapper {
    Feature map (com.mettle.userfeaturemanager.model.datastore.Feature feature);
    com.mettle.userfeaturemanager.model.datastore.Feature map (Feature feature);
}
