package com.mettle.userfeaturemanager.service;

import com.mettle.userfeaturemanager.model.FeatureScope;
import com.mettle.userfeaturemanager.model.communication.Feature;
import com.mettle.userfeaturemanager.model.mapper.FeatureMapper;
import com.mettle.userfeaturemanager.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private FeatureMapper featureMapper;

    @Autowired
    private UserService userService;

    public List<Feature> fetchFeatures(Long userId, boolean isAdmin) {
        if (isAdmin) {
            return featureRepository.findAll().stream()
                    .map(feature -> featureMapper.map(feature))
                    .collect(Collectors.toList());
        } else {
            List<Feature> allFeatures = userService.fetchUser(userId).getFeatures();
            allFeatures.addAll(fetchGloballyEnabledFeatures());
            return allFeatures;
        }
    }

    public Feature createFeature(Feature feature) {
        feature.setStatus(false);
        return featureMapper.map(featureRepository.save(featureMapper.map(feature)));
    }

    private List<Feature> fetchGloballyEnabledFeatures() {
        return featureRepository.findAllByScope(FeatureScope.GLOBAL).stream()
                .filter(com.mettle.userfeaturemanager.model.datastore.Feature::isStatus)
                .map(feature -> featureMapper.map(feature))
                .collect(Collectors.toList());
    }
}
