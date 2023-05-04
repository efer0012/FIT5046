package com.example.befit.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.befit.entity.Customer;
import com.example.befit.repository.CustomerRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerViewModel extends AndroidViewModel {
    private CustomerRepository cRepository;
    private LiveData<List<Customer>> allCustomers;

    public CustomerViewModel(Application application) {
        super(application);
        cRepository = new CustomerRepository(application);
        allCustomers = cRepository.getAllCustomers();
    }

    public CompletableFuture<Customer> findByIDFuture(final int customerId) {
        return cRepository.findByIDFuture(customerId);
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }

    public void insertCustomer(Customer customer) {
        cRepository.insertCustomer(customer);
    }

    public void deleteAllCustomers() {
        cRepository.deleteAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        cRepository.updateCustomer(customer);
    }
}
