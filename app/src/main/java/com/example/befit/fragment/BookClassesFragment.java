package com.example.befit.fragment;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.befit.adapter.RecyclerViewAdapter;
import com.example.befit.dao.ClassesDao;
import com.example.befit.database.AppDatabase;
import com.example.befit.databinding.BookClassesFragmentBinding;
import com.example.befit.entity.Classes;
import com.example.befit.model.BeFitClasses;
import com.example.befit.viewmodel.ClassesViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookClassesFragment extends Fragment {

    //Recycler View
    private ClassesViewModel classesViewModel;
    private RecyclerViewAdapter adapter;
    private RecyclerView bookClassesRecycleView;

    private BookClassesFragmentBinding addBinding;
    public BookClassesFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = BookClassesFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        //Recycler View

        //initialise recycler view
        bookClassesRecycleView = addBinding.recyclerViewBookClassesPage;

        //initialise adapter
        adapter = new RecyclerViewAdapter();
        addBinding.recyclerViewBookClassesPage.setAdapter(adapter);

        addBinding.recyclerViewBookClassesPage.setLayoutManager(new LinearLayoutManager(getActivity()));

        super.onCreate(savedInstanceState);
        classesViewModel = new ViewModelProvider(this).get(ClassesViewModel.class);
        classesViewModel.getAllClasses().observe(getViewLifecycleOwner(), new Observer<List<Classes>>() {
            @Override
            public void onChanged(List<Classes> beFitClasses) {
                // Update RecyclerView Adapter with new data
                adapter.setClasses(beFitClasses);
            }
        });

//        // Create a new thread to perform the operation (testing)
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // Perform database operation using Room's Executor
//                ClassesDao classesDao = AppDatabase.getInstance(getContext()).classesDao();
//                int totalClasses = classesDao.getTotalClasses();
//                Log.d("BeFiT", String.valueOf(totalClasses) );
//            }
//        }).start();



        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
