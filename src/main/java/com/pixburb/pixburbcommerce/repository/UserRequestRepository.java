package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.UserRequestModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRequestRepository extends CrudRepository<UserRequestModel, Long> {
    Optional<UserRequestModel> findByEmail(String email);
}
