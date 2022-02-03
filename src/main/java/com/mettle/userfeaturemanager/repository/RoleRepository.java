package com.mettle.userfeaturemanager.repository;

import com.mettle.userfeaturemanager.model.datastore.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
