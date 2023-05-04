package com.example.befit.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.befit.entity.Classes;
import com.example.befit.repository.ClassesRepository;

import java.util.List;

public class ClassesViewModel extends AndroidViewModel {
    private ClassesRepository repository;
    private LiveData<List<Classes>> allClasses;

    public ClassesViewModel(@NonNull Application application) {
        super(application);
        repository = new ClassesRepository(application);
        allClasses = repository.getAllClasses();
    }

    public void insertClasses(Classes beFitClass) {
        repository.insertClasses(beFitClass);
    }

    public void updateClasses(Classes beFitClass) {
        repository.updateClasses(beFitClass);
    }

    public void deleteClasses(Classes beFitClass) {
        repository.deleteClasses(beFitClass);
    }

    public LiveData<List<Classes>> getAllClasses() {
        return allClasses;
    }
}

