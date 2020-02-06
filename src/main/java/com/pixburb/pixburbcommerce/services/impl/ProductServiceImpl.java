package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.ProductRequestData;
import com.pixburb.pixburbcommerce.model.CategoryModel;
import com.pixburb.pixburbcommerce.model.ProductModel;
import com.pixburb.pixburbcommerce.repository.CategoryRepository;
import com.pixburb.pixburbcommerce.repository.ProductRepository;
import com.pixburb.pixburbcommerce.services.ProductService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean createProduct(ProductRequestData productRequestData) {
        if(productRequestData != null)
        {
            if(!productRequestData.getProductCode().isEmpty())
            {
                Optional<CategoryModel> categoryModel = categoryRepository.findByCategoryName(productRequestData.getCategoryName());
                ProductModel productModel = new ProductModel();
                productModel.setProductCode(productRequestData.getProductCode());
                productModel.setProductName(productRequestData.getProductName());
                productModel.setPrice(productRequestData.getPrice());
                productModel.setQuantity(productRequestData.getQuantity());
                productModel.setCategoryId(categoryModel.get());
                productRepository.save(productModel);
                return true;
            }
        }
        return false;
    }
}
