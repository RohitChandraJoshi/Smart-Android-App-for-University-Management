package com.example.Smart_University_Management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TimetableModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_module);
    }

    public void OnclickTimetable(View view){
        startActivity(new Intent(getApplicationContext(),Add_Timetable.class));

    }
    public void OnclickviewTimetable(View view){
        startActivity(new Intent(getApplicationContext(),ViewTimetable.class));
    }

}