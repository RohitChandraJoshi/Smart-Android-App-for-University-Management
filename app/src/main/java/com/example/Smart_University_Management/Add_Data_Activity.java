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

public class Add_Data_Activity extends AppCompatActivity {

    EditText txtName,txtSystemId,txtRollno, txtSubject ,txtMte,txtEte,txtCa,txtTotal,txtGrade;
    Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        txtName     = findViewById(R.id.edtName);
        txtSystemId    = findViewById(R.id.edtSystemid);
        txtRollno  = findViewById(R.id.edtrollno);
        txtSubject  = findViewById(R.id.edtsubject);
        txtMte  = findViewById(R.id.edtmte);
        txtEte  = findViewById(R.id.edtete);
        txtCa  = findViewById(R.id.edtca);
        txtTotal  = findViewById(R.id.edttotal);
        txtGrade  = findViewById(R.id.edtgrade);
        btn_insert = findViewById(R.id.btnInsert);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

        final String name = txtName.getText().toString().trim();
        final String SystemId = txtSystemId.getText().toString().trim();
        final String RollNo = txtRollno.getText().toString().trim();
        final String Subject = txtSubject.getText().toString().trim();
        final String Mte = txtMte.getText().toString().trim();
        final String Ete = txtEte.getText().toString().trim();
        final String Ca = txtCa.getText().toString().trim();
        final String Total = txtTotal.getText().toString().trim();
        final String Grade = txtGrade.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if(name.isEmpty()){
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(SystemId.isEmpty()){
            Toast.makeText(this, "Enter SystemID", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(RollNo.isEmpty()){
            Toast.makeText(this, "Enter Roll No", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Subject.isEmpty()){
            Toast.makeText(this, "Enter Subject", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Mte.isEmpty()){
            Toast.makeText(this, "Enter Mte Marks", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Ete.isEmpty()){
            Toast.makeText(this, "Enter Ete Marks", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Ca.isEmpty()){
            Toast.makeText(this, "Enter Ca Marks", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Total.isEmpty()){
            Toast.makeText(this, "Enter Total Marks", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(Grade.isEmpty()){
            Toast.makeText(this, "Enter Grade", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.6/attendanceApp/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){
                                Toast.makeText(Add_Data_Activity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(Add_Data_Activity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Data_Activity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("Name",name);
                    params.put("SystemId",SystemId);
                    params.put("RollNo",RollNo);
                    params.put("Subject",Subject);
                    params.put("Mte",Mte);
                    params.put("Ete",Ete);
                    params.put("Ca",Ca);
                    params.put("Total",Total);
                    params.put("Grade",Grade);




                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(Add_Data_Activity.this);
            requestQueue.add(request);



        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}