package com.example.befit.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.befit.dao.ClassesDao;
import com.example.befit.database.AppDatabase;
import com.example.befit.entity.Classes;
import com.example.befit.entity.Customer;

import java.util.List;

public class ClassesRepository {
    private ClassesDao classesDao;
    private LiveData<List<Classes>> allClasses;

    public ClassesRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        classesDao = database.classesDao();
        allClasses = classesDao.getAllClasses();
    }

    public LiveData<List<Classes>> getAllClasses() {
        return allClasses;
    }

    public void insertClasses(final Classes beFitClass){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                classesDao.insert(beFitClass);
            }
        });
    }

    public void deleteAllClasses(){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                classesDao.deleteAll();
            }
        });
    }

    public void deleteClasses(final Classes beFitClass){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                classesDao.delete(beFitClass);
            }
        });
    }

    public void updateClasses(final Classes beFitClass){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                classesDao.update(beFitClass);
            }
        });
    }
}

