package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.RoleData;
import com.pixburb.pixburbcommerce.model.OrganizationModel;
import com.pixburb.pixburbcommerce.model.RoleModel;
import com.pixburb.pixburbcommerce.repository.OrganizationRepository;
import com.pixburb.pixburbcommerce.repository.RoleRepository;
import com.pixburb.pixburbcommerce.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private OrganizationRepository organizationRepository;

    private RoleRepository roleRepository;

    public RoleServiceImpl(OrganizationRepository organizationRepository, RoleRepository roleRepository) {
        this.organizationRepository = organizationRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean createRole(RoleData roleData) {
        if (roleData != null) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRoleName(roleData.getRoleName());
            Set<OrganizationModel> organizationModels = new HashSet<>();
            if (roleData.getOrganizationName() != null) {
                Optional<OrganizationModel> organization = organizationRepository.findById(roleData.getOrganizationName());
                if (organization.isPresent()) {
                    organizationModels.add(organization.get());
                }
                roleModel.setOrganizations(organizationModels);
            }
            roleModel.setActive(true);
            roleRepository.save(roleModel);

            return true;

        }

        return false;
    }
}
