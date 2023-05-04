package com.example.befit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.befit.adapter.RecyclerViewAdapter;
import com.example.befit.databinding.ActivityMainBinding;
import com.example.befit.model.BeFitClasses;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private List<BeFitClasses> classes;
    private RecyclerViewAdapter adapter;
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_fragment,
                R.id.nav_club_fragment,
                R.id.nav_book_classes_fragment,
                R.id.nav_my_classes_fragment,
                R.id.nav_goal_fragment,
                R.id.nav_profile_fragment)
                //to display the Navigation button as a drawer symbol,not being shown as an Up button
                .setOpenableLayout(binding.drawerLayout)
                .build();
        FragmentManager fragmentManager= getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
                fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Sets up a Toolbar for use with a NavController.
        NavigationUI.setupWithNavController(binding.appBar.toolbar,navController,
                mAppBarConfiguration);
    }
}