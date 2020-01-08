package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.UserData;
import com.pixburb.pixburbcommerce.model.OrganizationModel;
import com.pixburb.pixburbcommerce.model.RoleModel;
import com.pixburb.pixburbcommerce.model.UserModel;
import com.pixburb.pixburbcommerce.model.UserRequestModel;
import com.pixburb.pixburbcommerce.repository.OrganizationRepository;
import com.pixburb.pixburbcommerce.repository.RoleRepository;
import com.pixburb.pixburbcommerce.repository.UserRepository;
import com.pixburb.pixburbcommerce.repository.UserRequestRepository;
import com.pixburb.pixburbcommerce.security.PasswordEncryption;
import com.pixburb.pixburbcommerce.services.OtpService;
import com.pixburb.pixburbcommerce.services.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class UserServiceImpl implements UserService {


    @Resource
    private OtpService otpServiceImpl;

    @Resource
    private PasswordEncryption passwordEncrytionImpl;

    private OrganizationRepository organizationRepository;

    private UserRepository userRepository;

    private UserRequestRepository userRequestRepository;

    private RoleRepository roleRepository;

    public UserServiceImpl(final OrganizationRepository organizationRepository, final UserRepository userRepository,
                           final UserRequestRepository userRequestRepository, final RoleRepository roleRepository) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.userRequestRepository = userRequestRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean login(final String email, final String password) {
        //login api
        Optional<UserModel> user = getUserRepository().findById(email);
        if(user.isPresent() && password.equals(passwordEncrytionImpl.decrypt(user.get().getPassword())))
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
            if(userData.getPassword()!=null)
            {
                userRequestModel.setPassword(passwordEncrytionImpl.encrypt(userData.getPassword()));
            }
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
    public boolean createUser(final UserData userData) {

        if(userData != null)
        {
            UserModel userModel = new UserModel();
            userModel.setFirstName(userData.getFirstName());
            userModel.setLastName(userData.getLastName());
            userModel.setEmail(userData.getEmail());
            if(userData.getPassword() != null) {
                userModel.setPassword(passwordEncrytionImpl.encrypt(userData.getPassword()));
            }
            userModel.setPhone(userData.getPhone());
            userModel.setCreatedBy(userData.getCreatedBy());
            if(userData.getOrganization() != null)
            {

                Optional<OrganizationModel> organizationModel = organizationRepository.findById(userData.getOrganization());
                if(organizationModel.isPresent()) {
                    userModel.setOrganization(organizationModel.get());
                }
            }

            if(userData.getRole() != null)
            {
                Optional<RoleModel> roleModel = roleRepository.findById(userData.getRole());
                if(roleModel.isPresent())
                {
                    userModel.setRole(roleModel.get());
                }
            }
            userModel.setVerifiedUser(true);
            userModel.setActive(true);
            userModel.setCreatedOn(new Date());

            try
            {
                userRepository.save(userModel);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<UserData> viewAllUsersByOrganization(final String organization) {
        Iterable<UserModel> userModels = userRepository.findAll();
        List<UserData> userDataList = new ArrayList<>();
        for(UserModel userModel : userModels)
        {
            if(userModel.getOrganization()!=null) {
                if (userModel.getOrganization().getOrganizationName().equals(organization)) {
                    UserData userData = new UserData();
                    userData.setEmail(userModel.getEmail());
                    userData.setFirstName(userModel.getFirstName());
                    userData.setLastName(userModel.getLastName());
                    userData.setPhone(userModel.getPhone());
                    userData.setRole(userModel.getRole().getRoleName());
                    userDataList.add(userData);
                }
            }
        }
        return userDataList;
    }

    @Override
    public UserData findUser(String email) {
        if(email != null)
        {
            Optional<UserModel> userModel = userRepository.findById(email);
            if(userModel.isPresent() && userModel.get().isActive())
            {
                UserData userData = new UserData();
                userData.setEmail(userModel.get().getEmail());
                userData.setFirstName(userModel.get().getFirstName());
                userData.setLastName(userModel.get().getLastName());
                userData.setPhone(userModel.get().getPhone());
                if(userModel.get().getRole()!=null) {
                    userData.setRole(userModel.get().getRole().getRoleName());
                }
                return userData;
            }
        }
        return null;
    }

    @Override
    public boolean deactivateUser(String email) {
        if(email != null) {
            Optional<UserModel> userModel = userRepository.findById(email);
            if(userModel.isPresent())
            {
                userModel.get().setActive(false);
                userRepository.save(userModel.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateUser(UserData userData) {
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
                if(userRequestModel.get().getPassword()!=null)
                {
                    userModel.setPassword(userRequestModel.get().getPassword());
                }
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

    public PasswordEncryption getPasswordEncrytionImpl() {
        return passwordEncrytionImpl;
    }

    public void setPasswordEncrytionImpl(final PasswordEncryption passwordEncrytionImpl) {
        this.passwordEncrytionImpl = passwordEncrytionImpl;
    }
}
