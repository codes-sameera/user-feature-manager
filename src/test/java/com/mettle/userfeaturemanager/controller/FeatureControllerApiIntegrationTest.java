package com.mettle.userfeaturemanager.controller;

import com.mettle.userfeaturemanager.TestUtils;
import com.mettle.userfeaturemanager.model.MyUserDetails;
import com.mettle.userfeaturemanager.model.communication.Feature;
import com.mettle.userfeaturemanager.model.datastore.Role;
import com.mettle.userfeaturemanager.model.datastore.User;
import com.mettle.userfeaturemanager.service.FeatureService;
import com.mettle.userfeaturemanager.service.RoleService;
import com.mettle.userfeaturemanager.service.UserService;
import com.mettle.userfeaturemanager.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FeatureControllerApiIntegrationTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeatureService featureService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private JwtUtil jwtUtil;

    private Feature feature;

    @BeforeEach
    void setup() {
        feature = new Feature();
        feature.setStatus(true);
        feature.setName("Hikaru's feature");

        User storedAdminUser = new User();
        storedAdminUser.setUsername("admin");
        storedAdminUser.setPassword("$2a$10$gH406Lv5Ow4qbRXJyA76cuRa2o3DXyOmJVpz30zoMo0Iu2OyDRiAC");
        Role role = new Role();
        role.setName("ADMIN");
        storedAdminUser.setRole(role);
        UserDetails adminUser = new MyUserDetails(storedAdminUser);

        when(featureService.createFeature(feature)).thenReturn(feature);
        when(jwtUtil.extractUsername("someJWTToken")).thenReturn("admin");
        when(userService.loadUserByUsername("admin")).thenReturn(adminUser);
        when(jwtUtil.validateToken("someJWTToken", adminUser)).thenReturn(true);
    }

    @Test
    void createFeatureTest() throws Exception {
        mockMvc.perform(post("/features")
                .contentType(APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer someJWTToken")
                .content(TestUtils.asJsonString(feature)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(featureService).createFeature(feature);
    }

    @Test
    void fetchFeaturesForLoggedInUserTest() throws Exception {

        List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);
        when(featureService.fetchFeatures("admin", true)).thenReturn(featureList);

        mockMvc.perform(get("/features")
                        .contentType(APPLICATION_JSON_UTF8)
                        .header("Authorization", "Bearer someJWTToken"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(featureService).fetchFeatures("admin", true);
    }


}