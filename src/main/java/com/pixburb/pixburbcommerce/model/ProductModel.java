package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;

    @Column(unique = true)
    private String productCode;

    private Double quantity;

    private Double price;

    @ManyToOne
    @JoinColumn
    private CategoryModel categoryId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CategoryModel getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryModel categoryId) {
        this.categoryId = categoryId;
    }
}
