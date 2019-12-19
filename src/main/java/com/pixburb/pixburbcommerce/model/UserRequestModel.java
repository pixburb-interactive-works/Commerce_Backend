package com.pixburb.pixburbcommerce.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_request")
public class UserRequestModel {

    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requestId;

    @Id
    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private Date requestedOn;

    private String phone;

    private String verificationOtp;

    private String requestLink;

    private boolean approvalStatus;

    private boolean active;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(final Long requestId) {
        this.requestId = requestId;
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

    public Date getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(final Date requestedOn) {
        this.requestedOn = requestedOn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getVerificationOtp() {
        return verificationOtp;
    }

    public void setVerificationOtp(final String verificationOtp) {
        this.verificationOtp = verificationOtp;
    }

    public String getRequestLink() {
        return requestLink;
    }

    public void setRequestLink(final String requestLink) {
        this.requestLink = requestLink;
    }

    public boolean isApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(final boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
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
        UserRequestModel that = (UserRequestModel) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserRequestModel{" +
                "requestId=" + requestId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", requestedOn=" + requestedOn +
                ", phone='" + phone + '\'' +
                ", verificationOtp='" + verificationOtp + '\'' +
                ", requestLink='" + requestLink + '\'' +
                ", approvalStatus=" + approvalStatus +
                ", active=" + active +
                '}';
    }
}
