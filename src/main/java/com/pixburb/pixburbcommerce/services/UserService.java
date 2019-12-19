package com.pixburb.pixburbcommerce.services;

import com.pixburb.pixburbcommerce.data.UserData;

public interface UserService {
    boolean login(String email, String password);

    boolean createUserRequest(UserData userData);

    boolean verifyUserRequest(String email, String otp);
}
