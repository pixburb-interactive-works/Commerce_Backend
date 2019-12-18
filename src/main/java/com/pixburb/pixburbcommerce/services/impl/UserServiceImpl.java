package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.UserData;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.services.OtpService;
import com.pixburb.pixburbcommerce.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {


    @Resource
    private OtpService otpServiceImpl;

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(final String email, final String password) {
        //login api
        Optional<UserModel> user = getUserRepository().findById(email);
        if(user.isPresent() && password.equals(user.get().getPassword()))
        {
                return true;
        }
        return false;
    }

    @Override
    public boolean createUser(final UserData userData) {
        Optional<UserModel> user = getUserRepository().findById(userData.getEmail());
        if(!user.isPresent()) {

            //create user api
            UserModel userModel = new UserModel();
            userModel.setEmail(userData.getEmail());
            userModel.setFirstName(userData.getFirstName());
            userModel.setLastName(userData.getLastName());
            userModel.setPassword(userData.getPassword());
            userModel.setPhone(userData.getPhone());
            userModel.setCreatedOn(new Date());
            userModel.setActive(true);

            userModel.setVerificationOtp(otpServiceImpl.generateOtp());
            //save user
            try {
                getUserRepository().save(userModel);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean verifyUser(String email, String otp) {
        //login api
        Optional<UserModel> user = getUserRepository().findById(email);
        if(user.isPresent() && otp.equals(user.get().getVerificationOtp()))
        {
            user.get().setVerifiedUser(true);
            try {
                getUserRepository().save(user.get());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }


    public OtpService getOtpServiceImpl() {
        return otpServiceImpl;
    }

    public void setOtpServiceImpl(OtpService otpServiceImpl) {
        this.otpServiceImpl = otpServiceImpl;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
