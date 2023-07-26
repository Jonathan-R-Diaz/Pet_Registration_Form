package com.zybooks.petregistrationform.repo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.zybooks.petregistrationform.model.PetEntry;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PetRepository {

    private static final String DATABASE_NAME = "petcare.db";
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService mDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static PetRepository mPetRepo;
    private final PetEntryDao mPetEntryDao;

    public static PetRepository getInstance(Context context) {
        System.out.println("PetRepository getInstance called");
        if (mPetRepo == null) {
            System.out.println("Creating new PetRepository");
            mPetRepo = new PetRepository(context);
        }
        return mPetRepo;
    }

    private PetRepository(Context context) {

        System.out.println("PetRepository object called");

        PetDatabase database = Room.databaseBuilder(context, PetDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        mPetEntryDao = database.petEntryDao();

        if (mPetEntryDao.getPetEntries().isEmpty()) {
            System.out.println("getPetEntries is empty");
            addStarterData();
        }
        else
            System.out.println("getPetEntries is not empty");
    }

    private void addStarterData() {
        // Add timo
        System.out.println("Adding starter data...");
        PetEntry timo = new PetEntry("TIM0T33","Timo", true, "jrd17j@fsu.edu", "American Eskimo", false);
        addPetEntry(timo);
    }

    public void addPetEntry(PetEntry pet) {
        mPetEntryDao.addPetEntry(pet);
    }

    public PetEntry getPetEntry(String petId) {
        return mPetEntryDao.getPetEntry(petId);
    }

    public List<PetEntry> getPetEntries() {
        return mPetEntryDao.getPetEntries();
    }
}
