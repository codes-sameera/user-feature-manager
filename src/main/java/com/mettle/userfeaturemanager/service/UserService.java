package com.mettle.userfeaturemanager.service;

import com.mettle.userfeaturemanager.model.MyUserDetails;
import com.mettle.userfeaturemanager.model.communication.*;
import com.mettle.userfeaturemanager.model.mapper.UserMapper;
import com.mettle.userfeaturemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User fetchUser (String username) {
        return userMapper.map(getUser(username));
    }

    public Status updateFeatures(Long userid, FeatureUpdatingInfo featureUpdatingInfo) {
        com.mettle.userfeaturemanager.model.datastore.User user = userRepository.findById(userid).get();
        user.getFeatures().addAll(
                featureUpdatingInfo.getFeatureIds().stream().map(featureId -> {
                    com.mettle.userfeaturemanager.model.datastore.Feature feature
                            = new com.mettle.userfeaturemanager.model.datastore.Feature();
                    feature.setId(featureId);
                    feature.setStatus(true);
                    return feature;
                }).collect(Collectors.toList())
        );
        userRepository.save(user);
        return new Status("Features added to user");
    }

    public Status updateRole(Long userid, RoleUpdatingInfo roleUpdatingInfo) {
        com.mettle.userfeaturemanager.model.datastore.Role role
                = new com.mettle.userfeaturemanager.model.datastore.Role();
        role.setId(roleUpdatingInfo.getRoleId());

        com.mettle.userfeaturemanager.model.datastore.User user = userRepository.findById(userid).get();
        user.setRole(role);

        userRepository.save(user);
        return new Status("Role added to user");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        getUser(username);
        return userRepository.findByUsername(username).map(MyUserDetails::new).get();
    }

    public User createUser(User user) {
        // By default, normal role is set
        Role role = new Role();
        role.setId(20000l);
        user.setRole(role);
        User createdUser = userMapper.map(userRepository.save(userMapper.map(user)));
        createdUser.setPassword(null);
        return createdUser;
    }

    private com.mettle.userfeaturemanager.model.datastore.User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exist"));
    }
}
