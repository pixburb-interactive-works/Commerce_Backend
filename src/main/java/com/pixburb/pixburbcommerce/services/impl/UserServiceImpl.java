package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.UserData;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.model.UserRequestModel;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.repository.UserRequestRepository;
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

    private UserRequestRepository userRequestRepository;


    public UserServiceImpl(UserRepository userRepository, UserRequestRepository userRequestRepository) {
        this.userRepository = userRepository;
        this.userRequestRepository = userRequestRepository;
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
    public boolean createUserRequest(final UserData userData) {
        Optional<UserModel> user = getUserRepository().findById(userData.getEmail());
        if(!user.isPresent()) {

            //create user api
            UserRequestModel userRequestModel = new UserRequestModel();
            userRequestModel.setEmail(userData.getEmail());
            userRequestModel.setFirstName(userData.getFirstName());
            userRequestModel.setLastName(userData.getLastName());
            userRequestModel.setPassword(userData.getPassword());
            userRequestModel.setPhone(userData.getPhone());
            userRequestModel.setRequestedOn(new Date());
            userRequestModel.setActive(true);
            userRequestModel.setVerificationOtp(otpServiceImpl.generateOtp());
            //save user
            try {
                getUserRequestRepository().save(userRequestModel);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean verifyUserRequest(String email, String otp) {
        //login api
        Optional<UserRequestModel> userRequestModel = getUserRequestRepository().findById(email);
        if(userRequestModel.isPresent() && otp.equals(userRequestModel.get().getVerificationOtp()))
        {
            userRequestModel.get().setApprovalStatus(true);
            try {
                //save UserRequestModel with approval status true
                getUserRequestRepository().save(userRequestModel.get());


                //create UserModel
                UserModel userModel = new UserModel();
                userModel.setEmail(userRequestModel.get().getEmail());
                userModel.setFirstName(userRequestModel.get().getFirstName());
                userModel.setLastName(userRequestModel.get().getLastName());
                userModel.setPassword(userRequestModel.get().getPassword());
                userModel.setPhone(userRequestModel.get().getPhone());
                userModel.setCreatedOn(new Date());
                userModel.setVerifiedUser(true);
                userModel.setActive(true);
                getUserRepository().save(userModel);

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
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

    public UserRequestRepository getUserRequestRepository() {
        return userRequestRepository;
    }

    public void setUserRequestRepository(UserRequestRepository userRequestRepository) {
        this.userRequestRepository = userRequestRepository;
    }
}
