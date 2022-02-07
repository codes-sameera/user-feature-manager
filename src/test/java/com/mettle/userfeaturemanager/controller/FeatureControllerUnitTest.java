package com.mettle.userfeaturemanager.controller;

import com.mettle.userfeaturemanager.model.MyUserDetails;
import com.mettle.userfeaturemanager.model.communication.Feature;
import com.mettle.userfeaturemanager.service.FeatureService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FeatureControllerUnitTest {

    @Mock
    private FeatureService featureService;

    @InjectMocks
    private FeatureController featureController;
    private Feature feature;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        feature = new Feature();
        feature.setStatus(true);
        feature.setName("Magnus");
    }

    @Test
    void createFeatureTest() {
        when(featureService.createFeature(feature)).thenReturn(feature);

        assertEquals(feature, featureController.createFeature(feature));

        verify(featureService).createFeature(feature);
    }

    @Test
    void fetchFeaturesForLoggedInUserTest() {
        Principal principal = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        MyUserDetails userDetails = Mockito.mock(MyUserDetails.class);
        when(((UsernamePasswordAuthenticationToken) principal).getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("Vishy");
        List authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        when(userDetails.getAuthorities()).thenReturn(authorities);
        List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);
        when(featureService.fetchFeatures("Vishy", true)).thenReturn(featureList);

        assertEquals(featureList, featureController.fetchFeaturesForLoggedInUser(principal));
        verify(featureService).fetchFeatures("Vishy", true);
    }
}