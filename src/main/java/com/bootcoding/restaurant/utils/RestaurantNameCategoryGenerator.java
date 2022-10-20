package com.bootcoding.restaurant.utils;

import java.util.*;

public class RestaurantNameCategoryGenerator {

    private static final Map<String, String> namesWithCategory = new LinkedHashMap<>();
    private static final List<String> vendorNames = new ArrayList<>();
    private static final String GENERAL = "General";
    static {
        namesWithCategory.put("Hotel Al-Zam Zam", GENERAL);
        namesWithCategory.put("Laziz Food", "Biryani");
        namesWithCategory.put("Pintu Saoji Restaurant", GENERAL);
        namesWithCategory.put("Pizza Hut", "Pizza");
        namesWithCategory.put("Domino's Pizza", "Pizza");
        namesWithCategory.put("Laxmi Family Restaurant", GENERAL);
        namesWithCategory.put("Barbeque", "Barbeque Nation");
        namesWithCategory.put("Ashoka Restaurant", GENERAL);
        namesWithCategory.put("Haldiram", "Pure Veg");
        namesWithCategory.put("Hotel Mejwani", GENERAL);
        namesWithCategory.put("McDonalds", GENERAL);
        namesWithCategory.put("Veggietude", "Pure Veg");

       vendorNames.addAll(namesWithCategory.keySet());
    }


    public static String getName(){
        Random random = new Random();
        int randomIndex = random.nextInt(vendorNames.size());
        String name = vendorNames.get(randomIndex);
        return name;
    }
    public static String getCategory(String name){
        return namesWithCategory.get(name);
    }

}
