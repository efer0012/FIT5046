package com.example.befit;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        EditText emailEditText= findViewById(R.id.emailEditText);
        EditText passwordEditText= findViewById(R.id.passwordEditText);

        //TODO: add validation.
        EditText firstNameEditText= findViewById(R.id.firstNameEditText);
        String first_name_txt = firstNameEditText.getText().toString(); //TODO
        //TODO: add validation.
        EditText lastNameEditText= findViewById(R.id.lastNameEditText);
        String last_name_txt = lastNameEditText.getText().toString(); //TODO

        Button dobPicker = findViewById(R.id.dobPicker);
        TextView dateOfBirth = findViewById(R.id.dateOfBirth);
        dobPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the instance of our calendar.
                final Calendar c = Calendar.getInstance();
                int dob_year = c.get(Calendar.YEAR); //TODO
                int dob_month = c.get(Calendar.MONTH); //TODO
                int dob_day = c.get(Calendar.DAY_OF_MONTH); //TODO
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOfBirth.setText("Date of Birth: " + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year); //TODO: format?
                    }},
                        dob_year, dob_month, dob_day); // passing year, month and day of selected date.
                datePickerDialog.show();
            }
        });

        String[] genderOption = {"Male", "Female", "Other"};
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this ,android.R.layout.simple_spinner_item, genderOption);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        genderSpinner.setAdapter(spinnerAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = genderOption[position]; //TODO
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO: prompt?
            }
        });

        EditText addressEditText= findViewById(R.id.addressEditText);
        String address_txt = addressEditText.getText().toString(); //TODO

        Button registerButton=findViewById(R.id.submitButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_txt = emailEditText.getText().toString(); //TODO
                String password_txt = passwordEditText.getText().toString(); //TODO
                if (TextUtils.isEmpty(email_txt) || TextUtils.isEmpty(password_txt)) {
                    toastMsg("Empty Username or Password");
                } else if (password_txt.length() < 8) {
                    toastMsg("Password must be at least 8 chars");
                } else
                    registerUser(email_txt, password_txt);
            }
        });

    }
    private void registerUser(String email_txt, String password_txt) {
        // create username and password
        auth.createUserWithEmailAndPassword(email_txt,password_txt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String msg = "Registration Successful";
                    startActivity(new Intent(SignupActivity.this, LaunchActivity.class));
                }else {
                    String msg = "Registration Unsuccessful";
                    toastMsg(msg);
                }
            }
        });
    }
    public void toastMsg(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
