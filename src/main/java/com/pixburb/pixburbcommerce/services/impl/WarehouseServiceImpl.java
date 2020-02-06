package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.WarehouseRequestData;
import com.pixburb.pixburbcommerce.model.OrganizationModel;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.model.WarehouseModel;
import com.pixburb.pixburbcommerce.repository.OrganizationRepository;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.repository.WarehouseRepository;
import com.pixburb.pixburbcommerce.services.WarehouseService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WarehouseServiceImpl implements WarehouseService {


    private WarehouseRepository warehouseRepository;

    private OrganizationRepository organizationRepository;

    private UserRepository userRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, OrganizationRepository organizationRepository, UserRepository userRepository) {
        this.warehouseRepository = warehouseRepository;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean createWarehouse(WarehouseRequestData warehouseRequestData) {
        if(warehouseRequestData != null)
        {
            if(!warehouseRequestData.getWarehouseName().isEmpty())
            {
                Optional<OrganizationModel> organizationModel = organizationRepository.findByOrganizationName(warehouseRequestData.getOrganizationName());
                Optional<UserModel> userModel = userRepository.findByEmail(warehouseRequestData.getUserEmailId());
                WarehouseModel warehouseModel = new WarehouseModel();
                warehouseModel.setWarehouseName(warehouseRequestData.getWarehouseName());
                warehouseModel.setOrganizationId(organizationModel.get());
                warehouseModel.setUserId(userModel.get());
                warehouseRepository.save(warehouseModel);
                return true;
            }
        }
        return false;
    }
}
