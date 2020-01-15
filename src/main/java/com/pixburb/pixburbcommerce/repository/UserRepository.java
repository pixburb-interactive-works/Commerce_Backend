package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

}
