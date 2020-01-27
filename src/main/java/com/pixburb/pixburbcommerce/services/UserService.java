package com.pixburb.pixburbcommerce.services;

import com.pixburb.pixburbcommerce.data.Login;
import com.pixburb.pixburbcommerce.data.UserRequestData;
import com.pixburb.pixburbcommerce.data.UserResponseData;

import java.util.List;

public interface UserService {
    String login(Login login);

    boolean createUserRequest(UserRequestData userRequestData);

    boolean createUser(UserRequestData userRequestData);

    List<UserRequestData> viewAllUsersByOrganization(String organization);

    UserResponseData findUser(String email);

    boolean  deactivateUser(String email);

    boolean updateUser(UserRequestData userRequestData);

    boolean verifyUserRequest(String email, String otp);
}
