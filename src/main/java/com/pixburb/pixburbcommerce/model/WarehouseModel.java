package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class WarehouseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long warehouseId;

    @Column(unique = true)
    private String warehouseName;

    @ManyToOne
    @JoinColumn
    private OrganizationModel organizationId;

    @OneToOne
    @JoinColumn
    private UserModel userId;

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public OrganizationModel getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(OrganizationModel organizationId) {
        this.organizationId = organizationId;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }
}
