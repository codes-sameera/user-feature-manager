package com.mettle.userfeaturemanager.model.communication;

import lombok.Data;

import java.util.List;

@Data
public class FeatureUpdatingInfo {
    private List<Long> featureIds;
}
