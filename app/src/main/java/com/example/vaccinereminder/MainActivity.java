package com.example.vaccinereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logout = findViewById(R.id.button);
        Button bcg = findViewById(R.id.bcg);
        Button hepatitisb = findViewById(R.id.hepatitisb);
        Button polio = findViewById(R.id.polio);
        Button hepatitisa = findViewById(R.id.hepatitisa);
        Button typhoid = findViewById(R.id.typhoid);
        Button dtp = findViewById(R.id.dtp);
        Button hib = findViewById(R.id.hib);
        Button pneumococcal = findViewById(R.id.pneumococcal);
        Button rotavirus = findViewById(R.id.rotavirus);
        Button measles = findViewById(R.id.measles);
        Button hpv = findViewById(R.id.hpv);
        Button cholera = findViewById(R.id.cholera);
        Button calendar = findViewById(R.id.calendar);
        Button add = findViewById(R.id.button2);
        Button view = findViewById(R.id.button3);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddChild.class));
            }
        });

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, ReminderBroadcast.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calender = Calendar.getInstance();
        calender.set(Calendar.HOUR_OF_DAY,15);
        calender.set(Calendar.MINUTE,25);
        calender.set(Calendar.SECOND,0);
        calender.set(Calendar.AM_PM,Calendar.AM);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), broadcast);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewAddedChild.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();//logout
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        bcg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),bcg.class));
            }
        });

        hepatitisb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hepatitisb.class));
            }
        });

        polio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),polio.class));
            }
        });

        hepatitisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hepatitisa.class));
            }
        });

        typhoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),typhoid.class));
            }
        });

        pneumococcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),pneumococcal.class));
            }
        });

        dtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),dtp.class));
            }
        });

        hib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hib.class));
            }
        });

        rotavirus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),rotavirus.class));
            }
        });

        measles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),measles.class));
            }
        });


        hpv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),hpv.class));
            }
        });



        cholera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),cholera.class));
            }
        });


        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),calendar.class));
            }
        });

    }
}