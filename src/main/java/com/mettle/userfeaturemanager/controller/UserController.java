package com.mettle.userfeaturemanager.controller;

import com.mettle.userfeaturemanager.model.communication.FeatureUpdatingInfo;
import com.mettle.userfeaturemanager.model.communication.RoleUpdatingInfo;
import com.mettle.userfeaturemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userid}/features")
    public boolean addFeatures(@PathVariable Long userid, @RequestBody FeatureUpdatingInfo featureUpdatingInfo) {
        return userService.updateFeatures(userid, featureUpdatingInfo);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{userid}/role")
    public boolean updateRole(@PathVariable Long userid, @RequestBody RoleUpdatingInfo roleUpdatingInfo) {
        return userService.updateRole(userid, roleUpdatingInfo);
    }
}
