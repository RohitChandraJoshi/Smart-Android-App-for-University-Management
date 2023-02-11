package com.example.Smart_University_Management;




import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentMarks extends AppCompatActivity {
    EditText editTextname;
    Button buttonfetch;
    ListView listview;
    String SystemID;
    ProgressDialog mProgressDialog;

    public static final String KEY_SystemId = "SystemId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);
        editTextname = (EditText)findViewById(R.id.etsystemid);
        buttonfetch = (Button)findViewById(R.id.btnfetch);
        listview = (ListView)findViewById(R.id.listView2);
        buttonfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SystemID = editTextname.getText().toString().trim();


                if (SystemID.equals("")){
                    Toast.makeText(StudentMarks.this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
                }else {

                    GetMatchData();
                }

            }
        });
    }

    private void GetMatchData() {

        SystemID = editTextname.getText().toString().trim();


        mProgressDialog = new ProgressDialog(StudentMarks.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage(getString(R.string.progress_detail));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config8.MATCHDATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {

                            showJSON(response);
                            mProgressDialog.dismiss();

                        } else {

                            showJSON(response);
                            mProgressDialog.dismiss();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentMarks.this, ""+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_SystemId, SystemID);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config8.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Name = jo.getString(Config8.KEY_Name);
                String systemid = jo.getString(Config8.KEY_System);
                String RollNo = jo.getString(Config8.KEY_RollNo);
                String Subject = jo.getString(Config8.KEY_Subject);
                String Mte = jo.getString(Config8.KEY_Mte);
                String Ete = jo.getString(Config8.KEY_Ete);
                String Ca = jo.getString(Config8.KEY_Ca);
                String Total = jo.getString(Config8.KEY_Total);
                String Grade = jo.getString(Config8.KEY_Grade);





                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config8.KEY_Name, "Name = " + Name);
                employees.put(Config8.KEY_System, "System id = " + systemid);
                employees.put(Config8.KEY_RollNo, "RollNo = " +RollNo);
                employees.put(Config8.KEY_Subject, "Subject = " +Subject);
                employees.put(Config8.KEY_Mte, "Mte = " +Mte);
                employees.put(Config8.KEY_Ete, "Ete = " +Ete);
                employees.put(Config8.KEY_Ca, "Ca = " +Ca);
                employees.put(Config8.KEY_Total, "Total = " +Total);
                employees.put(Config8.KEY_Grade, "Grade = " +Grade);



                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                StudentMarks.this, list, R.layout.list_item4,
                new String[]{Config8.KEY_Name,Config8.KEY_System, Config8.KEY_RollNo, Config8.KEY_Subject,Config8.KEY_Mte,Config8.KEY_Ete,Config8.KEY_Ca,Config8.KEY_Total,Config8.KEY_Grade},
                new int[]{R.id.tvname,R.id.tvsys,  R.id.tvrollno, R.id.tvsubject,R.id.tvmte, R.id.tvete, R.id.tvca, R.id.tvtotal,R.id.tvgrade});

        listview.setAdapter(adapter);

    }


}