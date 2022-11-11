package com.bootcoding.restaurant.exercise;

public class ArrayExercise {
    public static void main(String[] args) {

        // Array declare + initialize
        int[] numbers = {10, 20, 30, 40, 50};
        // Array declare
        int[] ages = new int[5];
        // Add elements to Array (Initialization)
//        ages[0] = 10;
//        ages[1] = 20;
//        ages[2] = 30;
//        ages[3] = 40;
//        ages[4] = 50;
        for (int i = 0; i < ages.length; i++) {
            ages[i] = (i + 1) * 10;
        }

        // How to traverse array elements
        for (int i = 0; i < ages.length; i++) {
            System.out.println(i + " = " + ages[i]);
        }

        String[] months = {"January", "February"
                , "March", "April", "May"
                , "June", "July", "August"
                , "September", "October"
                , "November", "December"};

        for (int i = 0; i < months.length; i++) {
            System.out.println(months[i]);
        }


    }
}
