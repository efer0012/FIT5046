package com.example.befit.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.befit.entity.Customer;

import java.util.List;

@Dao
public interface CustomerDao {
    @Insert
    void insert(Customer customer);

    @Update
    void update(Customer customer);

    @Delete
    void delete(Customer customer);

    @Query("SELECT * FROM customer")
    LiveData<List<Customer>> getAllCustomers();

    @Query("SELECT * FROM customer WHERE customerId = :customerId LIMIT 1")
    Customer findByID(int customerId);

    @Query("DELETE FROM customer")
    void deleteAll();
}
