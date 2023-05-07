package com.example.befit.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Customer")
public class Customer {
    @PrimaryKey
    @NonNull
    public String email;
    public String firstName;
    public String lastName;
    public String gender;
    public String dateOfBirth;
    public String address;
    public double height; //TODO: need entry in sign up, or default 0?

    public Customer(@NonNull String email,
                    @NonNull String firstName,
                    @NonNull String lastName,
                    @NonNull String gender,
                    @NonNull String dateOfBirth,
                    @NonNull String address,
                    @NonNull double height) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.height = height;
    }
}
