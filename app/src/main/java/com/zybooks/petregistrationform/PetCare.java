//MODEL
package com.zybooks.petregistrationform;

import android.app.Application;
import android.content.Context;

import com.zybooks.petregistrationform.model.PetEntry;
import com.zybooks.petregistrationform.repo.PetRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetCare {
    private final PetRepository mPetRepo;
    public PetCare(Application application){
        System.out.println("Petcare object called");
        mPetRepo = PetRepository.getInstance(application.getApplicationContext());
    }

    public boolean regex(String input, String re){
        Pattern pt = Pattern.compile(re);
        Matcher mt = pt.matcher(input);
        return mt.matches();
    }

    public boolean checkMicroChip(String microchip){
        return checkDatabase(microchip) && checkFormatting(microchip);
    }

    public boolean checkDatabase(String microchip){
        List<PetEntry> pets = getPetRepo().getPetEntries();
        for (PetEntry pet : pets){
            if (pet.getId().equals(microchip)){
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

    public PetRepository getPetRepo(){
        return mPetRepo;
    }

}
