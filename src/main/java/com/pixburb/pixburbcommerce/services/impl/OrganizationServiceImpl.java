package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.OrganizationData;
import com.pixburb.pixburbcommerce.model.OrganizationModel;
import com.pixburb.pixburbcommerce.model.RoleModel;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.model.UserRequestModel;
import com.pixburb.pixburbcommerce.repository.OrganizationRepository;
import com.pixburb.pixburbcommerce.repository.RoleRepository;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.repository.UserRequestRepository;
import com.pixburb.pixburbcommerce.services.OrganizatonService;
import com.pixburb.pixburbcommerce.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;


@Component
public class OrganizationServiceImpl implements OrganizatonService {

    private OrganizationRepository organizationRepository;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private UserRequestRepository userRequestRepository;

    @Resource
    private UserService userServiceImpl;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, UserRepository userRepository, RoleRepository roleRepository, UserRequestRepository userRequestRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRequestRepository = userRequestRepository;
    }

    @Override
    public boolean createOrganization(OrganizationData organizationData) {
        if(organizationData!=null)
        {

            Optional<UserRequestModel> userRequestModel = getUserRequestRepository()
                    .findByEmail(organizationData.getCreatedBy());
            try {
                //create UserModel
                UserModel userModel = new UserModel();
                userModel.setEmail(userRequestModel.get().getEmail());
                userModel.setFirstName(userRequestModel.get().getFirstName());
                userModel.setLastName(userRequestModel.get().getLastName());
                if(userRequestModel.get().getPassword()!=null)
                {
                    userModel.setPassword(userRequestModel.get().getPassword());
                }
                userModel.setPhone(userRequestModel.get().getPhone());
                userModel.setCreatedOn(new Date());
                userModel.setVerifiedUser(true);
                userModel.setActive(true);
                Optional<RoleModel> roleModel = roleRepository.findByRoleName("ADMIN");
                if(roleModel.isPresent())
                {
                    userModel.setRole(roleModel.get());
                }
                getUserRepository().save(userModel);


                OrganizationModel organizationModel = new OrganizationModel();
                organizationModel.setOrganizationName(organizationData.getOrganizationName().toUpperCase());
                Set<UserModel> userModelSet = new HashSet<>();
                Optional<UserModel> user = userRepository.findByEmail(userModel.getEmail());
                if(user.isPresent())
                {
                    userModelSet.add(user.get());
                }
                organizationModel.setUsers(userModelSet);
                organizationModel.setActive(true);
                organizationRepository.save(organizationModel);

                Optional<OrganizationModel> organization = organizationRepository
                        .findByOrganizationName(organizationModel.getOrganizationName());
                if(organization.isPresent())
                {
                    userModel.setOrganizationId(organization.get());
                }
                userRepository.save(userModel);

                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public UserRequestRepository getUserRequestRepository() {
        return userRequestRepository;
    }

    public void setUserRequestRepository(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
