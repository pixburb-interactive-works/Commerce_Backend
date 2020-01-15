package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.OrganizationModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<OrganizationModel, Long> {

   Optional<OrganizationModel> findByOrganizationName(String name);
}
