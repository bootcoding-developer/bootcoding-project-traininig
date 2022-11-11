package com.bootcoding.restaurant.utils;

import java.util.Random;

public class QuantityGenerator {

    static Random random = new Random();

    public static int getQuantity(int bound) {
        return 1 + random.nextInt(bound);
    }

    public static int getQuantity(int lowerBound, int upperBound){
        return lowerBound + getQuantity(upperBound-lowerBound);
    }

    // Signature
    // Implementation
    // Calling - Invoation

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(QuantityGenerator.getQuantity(5));
        }
    }

}
