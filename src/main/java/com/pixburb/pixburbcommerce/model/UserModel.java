package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private String createdBy;

    private Date createdOn;

    private String phone;

    private boolean verifiedUser;


    @ManyToOne
    @JoinColumn
    private OrganizationModel organizationId;

    @ManyToOne
    @JoinColumn
    private RoleModel role;

    private boolean active;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public boolean isVerifiedUser() {
        return verifiedUser;
    }

    public void setVerifiedUser(final boolean verifiedUser) {
        this.verifiedUser = verifiedUser;
    }

    public OrganizationModel getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(OrganizationModel organizationId) {
        this.organizationId = organizationId;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(final RoleModel role) {
        this.role = role;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", createdBy=" + createdBy +
                ", createdOn=" + createdOn +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
