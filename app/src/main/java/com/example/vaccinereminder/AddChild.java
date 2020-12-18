package com.example.vaccinereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddChild extends AppCompatActivity {

    EditText date,name,email,gender,phone;
    int year;
    int month;
    int day;
    Button save;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        date = findViewById(R.id.dob);
        name = findViewById(R.id.addChild);
        email = findViewById(R.id.email);
        gender = findViewById(R.id.gender);
        save = findViewById(R.id.done);
        phone = findViewById(R.id.phoneNumber);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String childName = name.getText().toString();
                String EMAIL = email.getText().toString();
                String gen = gender.getText().toString();
                String number = phone.getText().toString();

                if(TextUtils.isEmpty(childName)){
                    name.setError("Enter Child's Name");
                    return;
                }

                if(TextUtils.isEmpty(EMAIL)){
                    email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(number)){
                    phone.setError("Phone Number is Required");
                    return;
                }

                if(TextUtils.isEmpty(gen)){
                    gender.setError("Enter Child's Gender");
                    return;
                }

                //Add Child Data to Firebase
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("Child").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("Child Name",childName);
                user.put("Email",EMAIL);
                user.put("Gender",gen);
                user.put("Phone Number",number);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG","onSuccess: User profile is created for "+userID);
                    }
                });
                Toast.makeText(AddChild.this,"Child Created",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        CountryCurrencyButton button = (CountryCurrencyButton) findViewById(R.id.button);
        button.setOnClickListener(new CountryCurrencyPickerListener() {
            @Override
            public void onSelectCountry(Country country) {
                if (country.getCurrency() == null) {
                    Toast.makeText(AddChild.this,
                            String.format("name: %s\ncode: %s", country.getName(), country.getCode())
                            , Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddChild.this,
                            String.format("name: %s\ncurrencySymbol: %s", country.getName(), country.getCurrency().getSymbol())
                            , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSelectCurrency(Currency currency) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddChild.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                }, year,month,day);
                datePickerDialog.show();

            }
        });
    }
}