package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.model.User;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.services.UserService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {



    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean login(String email, String password) {
        Optional<User> user = getUserRepository().findById(email);
        if(user.isPresent() && password.equals(user.get().getPassword()))
        {
                return true;
        }
        return false;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
