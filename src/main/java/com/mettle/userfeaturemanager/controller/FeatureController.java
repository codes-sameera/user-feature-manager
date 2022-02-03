package com.mettle.userfeaturemanager.controller;

import com.mettle.userfeaturemanager.model.MyUserDetails;
import com.mettle.userfeaturemanager.model.communication.Feature;
import com.mettle.userfeaturemanager.service.FeatureService;
import com.mettle.userfeaturemanager.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Feature createFeature(@RequestBody Feature feature) {
        return featureService.createFeature(feature);
    }

    @GetMapping
    public List<Feature> fetchFeaturesForLoggedInUser(Principal principal) {
        // if user is an admin, all features are returned
        return featureService.fetchFeatures(((MyUserDetails)((UsernamePasswordAuthenticationToken) principal)
                .getPrincipal()).getId(), UserUtils.isAdmin((UsernamePasswordAuthenticationToken) principal));
    }
}
