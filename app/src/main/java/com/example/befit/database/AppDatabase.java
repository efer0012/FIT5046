package com.example.befit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.befit.dao.BookingDao;
import com.example.befit.dao.ClassesDao;
import com.example.befit.dao.CustomerDao;
import com.example.befit.entity.Booking;
import com.example.befit.entity.Classes;
import com.example.befit.entity.Customer;
import com.example.befit.model.BeFitClasses;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Customer.class, Classes.class, Booking.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CustomerDao customerDao();
    public abstract ClassesDao classesDao();
    public abstract BookingDao bookingDao();

    private static AppDatabase INSTANCE;

    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads are not allowed to access data at the same time
    public static synchronized AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "AppDatabase")
                    .addCallback(new BeFitClasses()) //pre populate classes database
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
