package com.example.befit.fragment;

import com.example.befit.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import androidx.fragment.app.Fragment;

import com.example.befit.databinding.ClubFragmentBinding;

public class ClubFragment extends Fragment {
    private ClubFragmentBinding addBinding;
    public ClubFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = ClubFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        // Initialize the Mapbox map
        addBinding.clubMap.onCreate(savedInstanceState);
        addBinding.clubMap.getMapAsync(mapboxMap -> {
            // Set the map style
            mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> {
                // You can interact with the mapboxMap instance here
            });
        });

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        addBinding.clubMap.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        addBinding.clubMap.onResume();
    }

    @Override
    public void onPause() {
        addBinding.clubMap.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        addBinding.clubMap.onStop();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        addBinding.clubMap.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        addBinding.clubMap.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        addBinding.clubMap.onLowMemory();
    }

}