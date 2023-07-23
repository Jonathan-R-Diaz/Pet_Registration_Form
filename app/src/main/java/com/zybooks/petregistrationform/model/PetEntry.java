package com.zybooks.petregistrationform.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PetEntry {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NonNull
    private String mId;

    @ColumnInfo(name = "name")
    private String mName;
    private boolean mIsMale;

    @ColumnInfo(name = "gender")
    private String mGender;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name ="breed")
    private String mBreed;

    @ColumnInfo(name = "neutered")
    private boolean mNeutered;

    public PetEntry(@NonNull String id,
             @NonNull String name,
             boolean isMale,
             @NonNull String email,
             @NonNull String breed,
             boolean neutered){
        System.out.println("PetEntry object called:" + id + name + isMale + email + breed + neutered);
        mId = id;
        mName = name;
        mIsMale = isMale;
        mGender = getGender();
        mEmail = email;
        mBreed = breed;
        mNeutered = neutered;
    }

    public void setId(String id){
        mId = id;
    }
    public void setName(String name){
        mName = name;
    }
    public void setIsMale(boolean isMale){
        mIsMale = isMale;
    }
    public void setGender(String gender){
        mGender = gender;
    }

    public void setEmail(String email){
        mEmail = email;
    }
    public void setBreed(String breed){
        mBreed = breed;
    }
    public void setIsNeutered(boolean isNeutered){
        mNeutered = isNeutered;
    }

    public String getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
    public String getGender(){
        if (getIsMale()) return "Male";
        return "Female";
    }
    public boolean getIsMale(){ return mIsMale; }
    public String getBreed(){ return mBreed; }
    public String getEmail(){
        return mEmail;
    }
    public boolean getNeutered(){
        return mNeutered;
    }

}
