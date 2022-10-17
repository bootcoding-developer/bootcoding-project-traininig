package com.bootcoding.restaurant.exercise;

import java.util.ArrayList;

public class ArrayListExercise {

    public static void main(String[] args) {

        ArrayList ages = new ArrayList();
        //Add elements to ArrayList
        ages.add(10);
        ages.add(20);
        ages.add(30);
        ages.add(40);
        ages.add(50);

        for(int i=0; i< ages.size(); i++){
            System.out.println(ages.get(i));
        }

    }
}
