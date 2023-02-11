package com.example.Smart_University_Management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_attendance);
    }

    public void OnclickAttendance(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();

        if(button_text.equals("Attendance Date Wise"))
        {
            Intent hello = new Intent(this,MainActivity.class);
            startActivity(hello);
        }
        else if (button_text.equals("Attendance Name Wise"))
        {
             Intent mass = new Intent(this,NameAttendance.class);
            startActivity(mass);

        }
        else if (button_text.equals("Attendance Slot Wise"))
        {
            Intent mass = new Intent(this,SlotAttendance.class);
            startActivity(mass);

        }
        else if (button_text.equals("Present in University but Absent in Class"))
        {
            Intent mass = new Intent(this,CollegeAbsent.class);
            startActivity(mass);

        }
//        else if (button_text.equals("click third activity"))
//        {
//            Intent mass = new Intent(this,ThirdActivity.class);
//            startActivity(mass);
//
//        }
    }
}