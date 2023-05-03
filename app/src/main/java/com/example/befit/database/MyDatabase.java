package com.example.befit.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.befit.dao.RecordDao;
import com.example.befit.entity.Record;

import java.util.concurrent.Executor;

@Database(entities = {Record.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract RecordDao recordDao();

    private static volatile MyDatabase instance;

    public static synchronized MyDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "my_database")
                    .build();
        }
        return instance;
    }
}

