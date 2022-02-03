package com.mettle.userfeaturemanager.model.mapper;

import com.mettle.userfeaturemanager.model.communication.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role map (com.mettle.userfeaturemanager.model.datastore.Role role);
    com.mettle.userfeaturemanager.model.datastore.Role map (Role role);
}
