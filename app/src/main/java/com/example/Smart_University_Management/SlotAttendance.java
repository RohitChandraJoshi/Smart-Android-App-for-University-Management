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

public class SlotAttendance extends AppCompatActivity {
    EditText editTextname;
    Button buttonfetch;
    ListView listview;
    String Subject;
    ProgressDialog mProgressDialog;

    public static final String KEY_Name = "Subject";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_attendance);
        editTextname = (EditText)findViewById(R.id.etdate);
        buttonfetch = (Button)findViewById(R.id.btnfetch);
        listview = (ListView)findViewById(R.id.listView2);
        buttonfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Subject = editTextname.getText().toString().trim();


                if (Subject.equals("")){
                    Toast.makeText(SlotAttendance.this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
                }else {

                    GetMatchData();
                }

            }
        });
    }

    private void GetMatchData() {

        Subject = editTextname.getText().toString().trim();


        mProgressDialog = new ProgressDialog(SlotAttendance.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage(getString(R.string.progress_detail));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config7.MATCHDATA_URL,
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
                        Toast.makeText(SlotAttendance.this, ""+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_Name, Subject);

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
            JSONArray result = jsonObject.getJSONArray(Config7.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String Subject = jo.getString(Config7.KEY_Name);
                String Date = jo.getString(Config7.KEY_D);
                String Day = jo.getString(Config7.KEY_Day);
                String Time = jo.getString(Config7.KEY_Time);




                final HashMap<String, String> employees = new HashMap<>();
                employees.put(Config7.KEY_Name, "Name = " + Subject);
                employees.put(Config7.KEY_D, "Date = " + Date);
                employees.put(Config7.KEY_Day, "Day = " +Day);
                employees.put(Config7.KEY_Time, "Time = " +Time);

                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                SlotAttendance.this, list, R.layout.list_item3,
                new String[]{Config7.KEY_Name,Config7.KEY_D, Config7.KEY_Day, Config7.KEY_Time},
                new int[]{R.id.tvname, R.id.tvdate, R.id.tvday, R.id.tvtime});

        listview.setAdapter(adapter);

    }


}