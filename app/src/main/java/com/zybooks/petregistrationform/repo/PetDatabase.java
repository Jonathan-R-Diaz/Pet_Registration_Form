package com.zybooks.petregistrationform.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.zybooks.petregistrationform.model.PetEntry;

@Database(entities = {PetEntry.class}, version = 1)
public abstract class PetDatabase extends RoomDatabase {
    public PetDatabase(){
        System.out.println("PetDatabase object called");
    }
    public abstract PetEntryDao petEntryDao();
}
