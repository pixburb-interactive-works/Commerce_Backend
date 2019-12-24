package com.pixburb.pixburbcommerce.security;

public interface PasswordEncryption {

    void setKey(String key);

    String encrypt(String input);

    String decrypt(String input);


}
