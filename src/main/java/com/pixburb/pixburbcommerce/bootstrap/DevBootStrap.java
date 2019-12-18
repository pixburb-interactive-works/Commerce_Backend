package com.pixburb.pixburbcommerce.bootstrap;

import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    public DevBootStrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init()
    {
       UserModel userModel = new UserModel();
       userModel.setEmail("admin@org.com");
       userModel.setFirstName("admin");
       userModel.setLastName("");
       userModel.setPassword("password");
       userModel.setActive(true);
       userModel.setCreatedOn(new Date());
       userModel.setVerifiedUser(true);
       userRepository.save(userModel);

    }
}
