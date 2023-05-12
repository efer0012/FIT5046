package com.example.befit.database;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import com.example.befit.entity.Customer;
import com.example.befit.viewmodel.CustomerViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Firestore extends AppCompatActivity {

    private static final String TAG = Firestore.class.getName();

    //private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public void upload() {
        // set up Customer view model
        CustomerViewModel customerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(CustomerViewModel.class);

        // get all customers
        customerViewModel.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable final List<Customer> customers) {
                for (Customer c : customers) {
                    add(c);
                }
            }
        });
    }

    public void add(Customer customer) {
        // set up Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        // add Customer
        // If the document does not exist, it will be created.
        // If the document does exist, its contents will be overwritten with the newly provided data
        db.collection("customers")
                .document(customer.email)
                .set(customer)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    private Customer customerR;

    public Customer retrieve(String email) {
        // set up
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

//         get Customer
        DocumentReference customerRef = db.collection("customers").document(email);
        customerRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot customerDoc = task.getResult();
                    customerR = customerDoc.toObject(Customer.class);
                    if (customerDoc.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + customerDoc.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        return customerR;
    }

    public void getUserInfo(String email, final OnGetDataListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("customers").document(email);;
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        listener.onSuccess(document);
                    } else {
                        listener.onFailure();
                    }
                } else {
                    listener.onFailure();
                }
            }
        });
    }

    public interface OnGetDataListener {
        void onSuccess(DocumentSnapshot document);
        void onFailure();
    }

}
//        Get all the Customer info [Lee]
//        CollectionReference customerRef = db.collection("customers");
//        customerRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    List<String> list = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : task.getResult())
//                    {
//                        list.add(document.getId());
//                    } Log.d(TAG, list.toString());
//
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
//        return customerR;
//    }
//}
