package com.pixburb.pixburbcommerce.services;

import com.pixburb.pixburbcommerce.data.UserData;

public interface UserService {
    boolean login(String email, String password);

    boolean createUser(UserData userData);

    boolean verifyUser(String email, String otp);
}
