package com.example.Smart_University_Management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MarksModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_module);
    }

    public void OnclickMarks(View view){
        startActivity(new Intent(getApplicationContext(),Add_Data_Activity.class));

    }
    public void OnclickviewMarks(View view){
        startActivity(new Intent(getApplicationContext(),StudentMarks.class));
    }

}