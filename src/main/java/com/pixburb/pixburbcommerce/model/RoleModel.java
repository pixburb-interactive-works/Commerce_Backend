package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleModel {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserModel> users;


    private boolean active;

    @ManyToMany
    @JoinTable(name = "organization_roles", joinColumns = @JoinColumn(name = "organizationName"), inverseJoinColumns = @JoinColumn(name="roleName"))
    private Set<OrganizationModel> organizations;

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public Set<OrganizationModel> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<OrganizationModel> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleModel roleModel = (RoleModel) o;
        return Objects.equals(roleName, roleModel.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
