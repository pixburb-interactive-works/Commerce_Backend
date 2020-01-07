package com.pixburb.pixburbcommerce.services;

import com.pixburb.pixburbcommerce.data.UserData;

import java.util.List;

public interface UserService {
    boolean login(String email, String password);

    boolean createUserRequest(UserData userData);

    boolean createUser(UserData userData);

    List<UserData> viewAllUsersByOrganization(String organization);

    UserData findUser(String email);

    boolean  deactivateUser(String email);

    boolean updateUser(UserData userData);

    boolean verifyUserRequest(String email, String otp);
}
