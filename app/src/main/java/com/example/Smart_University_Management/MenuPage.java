package com.example.Smart_University_Management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
    }

    public void btn_marksmodule(View view){
        startActivity(new Intent(getApplicationContext(),MarksModule.class));
    }
    public void btn_Timetablemodule(View view){
        startActivity(new Intent(getApplicationContext(),TimetableModule.class));
    }
    public void OnclickAttendance(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if(button_text.equals("Attendance"))
        {
            Intent hello = new Intent(this,SelectAttendance.class);
            startActivity(hello);
        }
//        else if (button_text.equals("StudentMarks"))
//        {
//            Intent mass = new Intent(this,MarksModule.class);
//            startActivity(mass);
//
//        }
        /*else if (button_text.equals("click third activity"))
        {
            Intent mass = new Intent(this,ThirdActivity.class);
            startActivity(mass);

        }*/
    }
}