package com.example.befit.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Record {
    @PrimaryKey(autoGenerate = true)
    public long date;
    public float height;
    public float weight;

    public Record(@NonNull long date, @NonNull float height, float weight) {
        this.date=date;
        this.height=height;
        this.weight = weight;
    }
        public long getDate() {
            return date;
        }

        public float getWeight() {
            return weight;
        }
    }
