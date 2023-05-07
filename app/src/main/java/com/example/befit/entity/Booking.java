package com.example.befit.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Customer.class,
                parentColumns = "email",
                childColumns = "customerEmail",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Classes.class,
                parentColumns = "classId",
                childColumns = "classId",
                onDelete = ForeignKey.CASCADE)
})
public class Booking {
    @PrimaryKey
    public int bookingId;
    public String customerEmail;
    public int classId;

    public Booking(@NonNull String customerEmail,
                 @NonNull int classId) {
        this.customerEmail = customerEmail;
        this.classId = classId;
    }
}
