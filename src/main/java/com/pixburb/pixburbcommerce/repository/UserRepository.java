package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.UserModel;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserModel, String> {
}
