package com.example.befit.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Customer.class,
                parentColumns = "customerId",
                childColumns = "customerId",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Classes.class,
                parentColumns = "classId",
                childColumns = "classId",
                onDelete = ForeignKey.CASCADE)
})
public class Booking {
    @PrimaryKey
    public int bookingId;
    public int customerId;
    public int classId;

    public Booking(@NonNull int customerId,
                 @NonNull int classId) {
        this.customerId = customerId;
        this.classId = classId;
    }
}
