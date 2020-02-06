package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column(unique = true)
    private String categoryName;

    @ManyToOne
    @JoinColumn
    private WarehouseModel warehouseId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public WarehouseModel getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(WarehouseModel warehouseId) {
        this.warehouseId = warehouseId;
    }
}
