package com.pixburb.pixburbcommerce.repository;

import com.pixburb.pixburbcommerce.model.CategoryModel;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {
    Optional<CategoryModel> findByCategoryName(String name);
}
