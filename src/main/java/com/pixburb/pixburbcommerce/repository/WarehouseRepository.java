package com.pixburb.pixburbcommerce.repository;


import com.pixburb.pixburbcommerce.model.WarehouseModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WarehouseRepository extends CrudRepository<WarehouseModel, Long> {
    Optional<WarehouseModel> findByWarehouseName(String name);
}
