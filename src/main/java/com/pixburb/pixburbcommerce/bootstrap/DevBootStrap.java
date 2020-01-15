package com.pixburb.pixburbcommerce.bootstrap;

import com.pixburb.pixburbcommerce.model.RoleModel;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.repository.RoleRepository;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.security.PasswordEncryption;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Resource
    private PasswordEncryption passwordEncrytionImpl;

    public DevBootStrap(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
       userModel.setPassword(passwordEncrytionImpl.encrypt("password"));
       userModel.setActive(true);
       userModel.setCreatedOn(new Date());
       userModel.setVerifiedUser(true);
       userRepository.save(userModel);

       RoleModel roleModelSuperAdmin = new RoleModel();
       roleModelSuperAdmin.setActive(true);
       roleModelSuperAdmin.setRoleName("SUPER-ADMIN");
       
       RoleModel roleModelAdmin = new RoleModel();
       roleModelAdmin.setActive(true);
       roleModelAdmin.setRoleName("ADMIN");


       RoleModel roleModelManager = new RoleModel();
       roleModelManager.setRoleName("MANAGER");
       roleModelManager.setActive(true);

       roleRepository.save(roleModelSuperAdmin);
       roleRepository.save(roleModelAdmin);
       roleRepository.save(roleModelManager);

    }

    public PasswordEncryption getPasswordEncrytionImpl() {
        return passwordEncrytionImpl;
    }

    public void setPasswordEncrytionImpl(final PasswordEncryption passwordEncrytionImpl) {
        this.passwordEncrytionImpl = passwordEncrytionImpl;
    }
}
