package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "organizations")
public class OrganizationModel {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    private String organizationName;

    @ManyToMany
    @JoinTable(name = "user_organization", joinColumns = @JoinColumn(name = "organizationName"),
    inverseJoinColumns = @JoinColumn(name="email"))
    private Set<UserModel> users;

    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", active=" + active +
                '}';
    }
}
