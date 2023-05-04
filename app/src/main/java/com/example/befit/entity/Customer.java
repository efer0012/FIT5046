package com.example.befit.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Customer")
public class Customer {
    @PrimaryKey
    public int customerId;
    public String firstName;
    public String lastName;
    public String email;
    public String gender;
    public String dateOfBirth;
    public String address;
    public double height;

    public Customer(@NonNull String firstName,
                    @NonNull String lastName,
                    @NonNull String email,
                    @NonNull String gender,
                    @NonNull String dateOfBirth,
                    @NonNull String address,
                    @NonNull double height) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.height = height;
    }
}
