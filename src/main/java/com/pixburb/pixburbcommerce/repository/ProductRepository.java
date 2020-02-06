package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.ProductModel;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductModel, Long> {

    Optional<ProductModel> findByProductCode(String code);
}
