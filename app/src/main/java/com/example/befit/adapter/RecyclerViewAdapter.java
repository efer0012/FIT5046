package com.example.befit.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.befit.databinding.CardLayoutBinding;
import com.example.befit.entity.Classes;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter
        <RecyclerViewAdapter.ViewHolder> {
    private List<Classes> beFitClasses;

    public RecyclerViewAdapter() {

    }

    //creates a new viewholder that is constructed with a new View, inflated from a layout
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        CardLayoutBinding binding=
                CardLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int
            position) {
        viewHolder.binding.cardClassName.setText(beFitClasses.get(position).className);
        viewHolder.binding.cardClassTime.setText(beFitClasses.get(position).startTime);

//        viewHolder.binding.ivItemDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                courseResults.remove(unit);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (beFitClasses == null){
            Log.d("BeFiT", "null zzz");
            return 0;}
        else
            return beFitClasses.size();
    }

    public void setClasses(List<Classes> beFitClasses) {
        this.beFitClasses = beFitClasses;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardLayoutBinding binding;
        public ViewHolder(CardLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
