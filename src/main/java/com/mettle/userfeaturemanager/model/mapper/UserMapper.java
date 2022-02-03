package com.mettle.userfeaturemanager.model.mapper;

import com.mettle.userfeaturemanager.model.communication.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map (com.mettle.userfeaturemanager.model.datastore.User User);
    com.mettle.userfeaturemanager.model.datastore.User map (User User);
}
