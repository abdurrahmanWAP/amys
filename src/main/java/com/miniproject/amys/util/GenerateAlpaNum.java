package com.miniproject.amys.util;

import java.util.Random;

public class GenerateAlpaNum {

    public static String randomGenerate(){
        String alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(5);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alfabet.length());
            sb.append(alfabet.charAt(index));
        }
        return sb.toString();
    }
}
