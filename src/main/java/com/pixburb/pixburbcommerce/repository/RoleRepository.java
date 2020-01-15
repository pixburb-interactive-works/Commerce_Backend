package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.RoleModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(String name);
}
