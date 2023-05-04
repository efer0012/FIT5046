package com.example.befit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.befit.R;
import com.example.befit.model.BeFitClasses;
import com.example.befit.adapter.RecyclerViewAdapter;
import com.example.befit.databinding.HomeFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

//    private List<BeFitClasses> classes;
//    private RecyclerViewAdapter adapter;
//    private RecyclerView myClassesTodayRecycleView;
//    private RecyclerView availableClassesTodayRecycleView;

    private HomeFragmentBinding addBinding;
    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
//
//        //initialise recycler view
//        myClassesTodayRecycleView = addBinding.recyclerViewMyClassesToday;
//
//        //set layour manager and adapter
//        addBinding.recyclerViewMyClassesToday.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        classes = new ArrayList<BeFitClasses>();
//        classes = BeFitClasses.createContactsList();
//
//        adapter = new RecyclerViewAdapter(classes);
//        addBinding.recyclerViewMyClassesToday.setAdapter(adapter);


        return view;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
