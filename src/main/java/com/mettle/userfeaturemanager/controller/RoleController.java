package com.mettle.userfeaturemanager.controller;

import com.mettle.userfeaturemanager.model.communication.Role;
import com.mettle.userfeaturemanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<Role> fetchRoles() {
        return roleService.fetchRoles();
    }
}
