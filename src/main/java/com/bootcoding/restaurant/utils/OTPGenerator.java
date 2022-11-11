package com.bootcoding.restaurant.utils;

import java.util.Random;

public class OTPGenerator {

    final static int MAX = 999999;
    final static int MIN = 100000;
    static Random random = new Random();

    // Naming Convntion - Kaam - Naam
    public static int getRandomOTP() {
        int number = random.nextInt(MAX - MIN); // 899999
        int otp = MIN + number;
        //System.out.println(" OTP Number = " + otp);
        return otp;
    }

    // Signature
    // Implementation
    // Calling - Invoation

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            OTPGenerator.getRandomOTP();
        }
    }

}
