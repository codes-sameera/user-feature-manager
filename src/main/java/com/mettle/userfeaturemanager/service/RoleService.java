package com.mettle.userfeaturemanager.service;

import com.mettle.userfeaturemanager.model.communication.Role;
import com.mettle.userfeaturemanager.model.mapper.RoleMapper;
import com.mettle.userfeaturemanager.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public Role createRole(Role role) {
        return roleMapper.map(roleRepository.save(roleMapper.map(role)));
    }

    public List<Role> fetchRoles() {
        return roleRepository.findAll().stream().map(role -> roleMapper.map(role)).collect(Collectors.toList());
    }
}
