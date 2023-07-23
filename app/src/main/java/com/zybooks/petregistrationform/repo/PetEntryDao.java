package com.zybooks.petregistrationform.repo;

import androidx.room.*;
import com.zybooks.petregistrationform.model.PetEntry;
import java.util.List;

@Dao
public interface PetEntryDao {
    @Query("SELECT * FROM PetEntry WHERE id = :id")
    PetEntry getPetEntry(String id);

    @Query("SELECT * FROM PetEntry ORDER BY name")
    List<PetEntry> getPetEntries();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addPetEntry(PetEntry pet);

    @Update
    void updatePetEntry(PetEntry pet);

    @Delete
    void deletePetEntry(PetEntry pet);
}