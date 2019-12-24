package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.OrganizationData;
import com.pixburb.pixburbcommerce.model.OrganizationModel;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.model.UserRequestModel;
import com.pixburb.pixburbcommerce.repository.OrganizationRepository;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.repository.UserRequestRepository;
import com.pixburb.pixburbcommerce.services.OrganizatonService;
import com.pixburb.pixburbcommerce.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Component
public class OrganizationServiceImpl implements OrganizatonService {

    private OrganizationRepository organizationRepository;

    private UserRepository userRepository;

    @Resource
    private UserService userServiceImpl;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createOrganization(OrganizationData organizationData) {
        if(organizationData!=null)
        {
            OrganizationModel organizationModel = new OrganizationModel();
            organizationModel.setOrganizationName(organizationData.getOrganizationName());
            if(organizationData.getCreatedBy() != null)
            {
                Set<UserModel> userModelSet = new HashSet<>();
                Optional<UserModel> user = getUserRepository().findById(organizationData.getCreatedBy());
                if(user.isPresent())
                {
                    userModelSet.add(user.get());
                }
                organizationModel.setUsers(userModelSet);
            }
            organizationModel.setActive(true);
            organizationRepository.save(organizationModel);
            return true;
        }
        return false;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public void setOrganizationRepository(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
