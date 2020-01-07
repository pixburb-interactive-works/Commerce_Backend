package com.pixburb.pixburbcommerce.services.impl;

import com.pixburb.pixburbcommerce.services.OtpService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpServiceImpl implements OtpService {
    @Override
    public String generateOtp() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
