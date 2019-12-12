package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, String> {
}
