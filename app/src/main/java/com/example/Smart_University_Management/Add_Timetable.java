package com.example.Smart_University_Management;




import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_Timetable extends AppCompatActivity {

    EditText txtSec,txtDay,txtfirst,txtsecond,txtthird, txtfourth ,txtfifth,txtsixth,txtseventh,txteighth,txtninth;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_timetable);

        txtSec     = findViewById(R.id.edtSec);
        txtDay     = findViewById(R.id.edtDay);
        txtfirst     = findViewById(R.id.edtfirst);
        txtsecond    = findViewById(R.id.edtsecond);
        txtthird  = findViewById(R.id.edtthird);
        txtfourth  = findViewById(R.id.edtfourth);
        txtfifth  = findViewById(R.id.edtfifth);
        txtsixth  = findViewById(R.id.edtsixth);
        txtseventh  = findViewById(R.id.edtseventh);
        txteighth  = findViewById(R.id.edteighth);
        txtninth  = findViewById(R.id.edtninth);
        btn_insert = findViewById(R.id.btnInsert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String section = txtSec.getText().toString().trim();
        final String Day = txtDay.getText().toString().trim();
        final String first = txtfirst.getText().toString().trim();
        final String second = txtsecond.getText().toString().trim();
        final String third = txtthird.getText().toString().trim();
        final String fourth = txtfourth.getText().toString().trim();
        final String fifth = txtfifth.getText().toString().trim();
        final String sixth = txtsixth.getText().toString().trim();
        final String seventh = txtseventh.getText().toString().trim();
        final String eighth = txteighth.getText().toString().trim();
        final String ninth = txtninth.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(section.isEmpty()){
            Toast.makeText(this, "Enter Section", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Day.isEmpty()){
            Toast.makeText(this, "Enter Day", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(first.isEmpty()){
            Toast.makeText(this, "Enter First", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(second.isEmpty()){
            Toast.makeText(this, "Enter Second", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(third.isEmpty()){
            Toast.makeText(this, "Enter Third", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(fourth.isEmpty()){
            Toast.makeText(this, "Enter Fourth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(fifth.isEmpty()){
            Toast.makeText(this, "Enter Fifth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(sixth.isEmpty()){
            Toast.makeText(this, "Enter Sixth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(seventh.isEmpty()){
            Toast.makeText(this, "Enter Seventh", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(eighth.isEmpty()){
            Toast.makeText(this, "Enter Eighth", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(ninth.isEmpty()){
            Toast.makeText(this, "Enter Ninth", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.6/attendanceApp/InsertTimetable.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(Add_Timetable.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(Add_Timetable.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Timetable.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Section",section);
                    params.put("Day",Day);
                    params.put("First",first);
                    params.put("Second",second);
                    params.put("Third",third);
                    params.put("Fourth",fourth);
                    params.put("Fifth",fifth);
                    params.put("Sixth",sixth);
                    params.put("Seventh",seventh);
                    params.put("Eighth",eighth);
                    params.put("Ninth",ninth);





                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Add_Timetable.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}