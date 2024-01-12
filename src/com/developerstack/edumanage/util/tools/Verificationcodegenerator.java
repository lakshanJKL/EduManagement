package com.developerstack.edumanage.util.tools;

import java.util.Random;

public class Verificationcodegenerator {

    private final String number = "0123456789";
    public int getcode(int length){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char selectedchar = number.charAt(new Random().nextInt(10));
            if((i==0) && (48==(int)selectedchar)){
                selectedchar = number.charAt(new Random().nextInt(10-1+1)+1);
            }
            sb.append(selectedchar);// 0-9
        }
        return Integer.parseInt(sb.toString());
    }
}
