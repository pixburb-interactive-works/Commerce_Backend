package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.data.UserRequestData;
import com.pixburb.pixburbcommerce.data.UserResponseData;
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
        Optional<UserModel> user = getUserRepository().findByEmail(email);
        if(user.isPresent() && password.equals(passwordEncrytionImpl.decrypt(user.get().getPassword())))
        {
                return true;
        }
        return false;
    }

    @Override
    public boolean createUserRequest(final UserRequestData userRequestData) {
        Optional<UserModel> user = getUserRepository().findByEmail(userRequestData.getEmail());
        if(!user.isPresent()) {

            //create user api
            UserRequestModel userRequestModel = new UserRequestModel();
            userRequestModel.setEmail(userRequestData.getEmail());
            userRequestModel.setFirstName(userRequestData.getFirstName().toUpperCase());
            userRequestModel.setLastName(userRequestData.getLastName().toUpperCase());
            if(userRequestData.getPassword()!=null)
            {
                userRequestModel.setPassword(passwordEncrytionImpl.encrypt(userRequestData.getPassword()));
            }
            userRequestModel.setPhone(userRequestData.getPhone());
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
    public boolean createUser(final UserRequestData userRequestData) {

        if(userRequestData != null)
        {
            UserModel userModel = new UserModel();
            userModel.setFirstName(userRequestData.getFirstName().toUpperCase());
            userModel.setLastName(userRequestData.getLastName().toUpperCase());
            userModel.setEmail(userRequestData.getEmail());
            if(userRequestData.getPassword() != null) {
                userModel.setPassword(passwordEncrytionImpl.encrypt(userRequestData.getPassword()));
            }
            userModel.setPhone(userRequestData.getPhone());
            userModel.setCreatedBy(userRequestData.getCreatedBy());
            if(userRequestData.getOrganization() != null)
            {

                Optional<OrganizationModel> organizationModel = organizationRepository.
                        findByOrganizationName(userRequestData.getOrganization().toUpperCase());
                if(organizationModel.isPresent()) {
                    userModel.setOrganizationId(organizationModel.get());
                }
            }

            if(userRequestData.getRole() != null)
            {
                Optional<RoleModel> roleModel = roleRepository.findByRoleName(userRequestData.getRole().toUpperCase());
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
    public List<UserRequestData> viewAllUsersByOrganization(final String organization) {
        Iterable<UserModel> userModels = userRepository.findAll();
        List<UserRequestData> userRequestDataList = new ArrayList<>();
        Optional<OrganizationModel> organizationModel = organizationRepository.findByOrganizationName(organization.toUpperCase());
        if(organizationModel.isPresent()) {
            for (UserModel userModel : userModels) {
                if (userModel.getOrganizationId() != null) {
                    if (userModel.getOrganizationId().getOrganizationId().equals(organizationModel.get().getOrganizationId())) {
                        UserRequestData userRequestData = new UserRequestData();
                        userRequestData.setEmail(userModel.getEmail());
                        userRequestData.setFirstName(userModel.getFirstName());
                        userRequestData.setLastName(userModel.getLastName());
                        userRequestData.setPhone(userModel.getPhone());
                        userRequestData.setRole(userModel.getRole().getRoleName());
                        userRequestDataList.add(userRequestData);
                    }
                }
            }
        }
        return userRequestDataList;
    }

    @Override
    public UserResponseData findUser(String email) {
        if(email != null)
        {
            Optional<UserModel> userModel = userRepository.findByEmail(email);
            if(userModel.isPresent() && userModel.get().isActive())
            {
                UserResponseData userResponseData = new UserResponseData();
                userResponseData.setEmail(userModel.get().getEmail());
                userResponseData.setFirstName(userModel.get().getFirstName());
                userResponseData.setLastName(userModel.get().getLastName());
                userResponseData.setPhone(userModel.get().getPhone());
                if(userModel.get().getRole()!=null) {
                    userResponseData.setRoleId(userModel.get().getRole().getId());
                    userResponseData.setRoleName(userModel.get().getRole().getRoleName());
                }
                if(userModel.get().getOrganizationId()!=null)
                {
                    userResponseData.setOrganizationId(userModel.get().getOrganizationId().getOrganizationId());
                    userResponseData.setOrganizationName(userModel.get().getOrganizationId().getOrganizationName());
                }
                return userResponseData;
            }
        }
        return null;
    }

    @Override
    public boolean deactivateUser(String email) {
        if(email != null) {
            Optional<UserModel> userModel = userRepository.findByEmail(email);
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
    public boolean updateUser(UserRequestData userRequestData) {
        if(userRequestData !=null)
        {
            Optional<UserModel> userModel = userRepository.findByEmail(userRequestData.getEmail());
            if(userModel.isPresent())
            {
                userModel.get().setFirstName(userRequestData.getFirstName().toUpperCase());
                userModel.get().setLastName(userRequestData.getLastName().toUpperCase());
                userModel.get().setPhone(userRequestData.getPhone());
                userModel.get().setActive(true);
                if(userRequestData.getOrganization() != null)
                {
                    Optional<OrganizationModel> organizationModel = organizationRepository.
                            findByOrganizationName(userRequestData.getOrganization().toUpperCase());
                    if(organizationModel.isPresent()) {
                        userModel.get().setOrganizationId(organizationModel.get());
                    }
                }

                if(userRequestData.getRole() != null)
                {
                    Optional<RoleModel> roleModel = roleRepository.findByRoleName(userRequestData.getRole().toUpperCase());
                    if(roleModel.isPresent())
                    {
                        userModel.get().setRole(roleModel.get());
                    }
                }
                userRepository.save(userModel.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyUserRequest(String email, String otp) {
        //login api
        Optional<UserRequestModel> userRequestModel = getUserRequestRepository().findByEmail(email);
        if(userRequestModel.isPresent() && otp.equals(userRequestModel.get().getVerificationOtp()))
        {
            userRequestModel.get().setApprovalStatus(true);
            getUserRequestRepository().save(userRequestModel.get());
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
