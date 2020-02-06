package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.CategoryRequestData;
import com.pixburb.pixburbcommerce.model.CategoryModel;
import com.pixburb.pixburbcommerce.model.WarehouseModel;
import com.pixburb.pixburbcommerce.repository.CategoryRepository;
import com.pixburb.pixburbcommerce.repository.WarehouseRepository;
import com.pixburb.pixburbcommerce.services.CategoryService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private WarehouseRepository warehouseRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, WarehouseRepository warehouseRepository) {
        this.categoryRepository = categoryRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public boolean createCategory(CategoryRequestData categoryRequestData) {
        if(categoryRequestData != null)
        {
            if(!categoryRequestData.getCategoryName().isEmpty())
            {
                Optional<WarehouseModel> warehouseModel = warehouseRepository.findByWarehouseName(categoryRequestData.getCategoryName());
                CategoryModel categoryModel = new CategoryModel();
                categoryModel.setCategoryName(categoryRequestData.getCategoryName());
                categoryModel.setWarehouseId(warehouseModel.get());
                categoryRepository.save(categoryModel);
                return true;
            }
        }
        return false;
    }
}
