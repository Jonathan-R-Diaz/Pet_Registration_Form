//MODEL
package com.zybooks.petregistrationform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetCare {
    public PetCare(){}

    public boolean regex(String input, String re){
        Pattern pt = Pattern.compile(re);
        Matcher mt = pt.matcher(input);
        return mt.matches();
    }

    public boolean checkMicroChip(String microchip, String[] chips){
        return checkDatabase(microchip, chips) && checkFormatting(microchip);
    }

    public boolean checkDatabase(String microchip, String[] chips){
        for (String chip : chips){
            if (chip.equals(microchip)){
                return false;
            }
        }
        return true;
    }

    public boolean checkFormatting(String microchip){
        return regex(microchip,"^[A-Z0-9]{5,15}$");
    }

    public boolean checkName(String name){
        System.out.println("Name: " + name);
        return regex(name, "\\b[A-Z][a-zA-Z]*\\b(?:\\s+[A-Z][a-zA-Z]*\\b)*");
    }

    public boolean checkEmail(String email){
        System.out.println("Email: " + email);
        return regex(email, "^[A-Za-z0-9._%+-]{3,}@[A-Za-z0-9.-]+\\.(?:edu|co|com|gal)$");
    }

    public boolean checkCodes(int code1, int code2){
        System.out.println("Checking codes... ");
        return code1 == code2;
    }

    public boolean isBlank(String str){
        return str.isEmpty();
    }
}
