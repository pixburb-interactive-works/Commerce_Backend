package com.pixburb.pixburbcommerce.bootstrap;

import com.pixburb.pixburbcommerce.model.User;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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
       User user = new User();
       user.setEmail("admin@org.com");
       user.setFirstName("admin");
       user.setLastName("");
       user.setPassword("password");
       userRepository.save(user);

    }
}
