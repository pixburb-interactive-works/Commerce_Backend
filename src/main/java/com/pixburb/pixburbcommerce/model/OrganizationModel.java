package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "organizations")
public class OrganizationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long organizationId;

    @Column(unique = true)
    private String organizationName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserModel> users;

    @ManyToMany(mappedBy = "organizations")
    private Set<RoleModel> roles;

    private boolean active;

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationModel that = (OrganizationModel) o;
        return Objects.equals(organizationName, that.organizationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationName);
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "id=" + organizationId +
                ", organizationName='" + organizationName + '\'' +
                ", active=" + active +
                '}';
    }
}
