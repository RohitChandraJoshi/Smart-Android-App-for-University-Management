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

public class ViewTimetable extends AppCompatActivity {
    EditText editTextSection, editTextDay;
    Button buttonfetch;
    ListView listview;
    String Section, Day;
    ProgressDialog mProgressDialog;

    public static final String KEY_Section = "Section";
    public static final String KEY_Day = "Day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);
        editTextSection = (EditText)findViewById(R.id.etsection);
        editTextDay = (EditText)findViewById(R.id.etday);
        buttonfetch = (Button)findViewById(R.id.btnfetch);
        listview = (ListView)findViewById(R.id.listView2);
        buttonfetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Section = editTextSection.getText().toString().trim();
                Day = editTextDay.getText().toString().trim();


                if (Section.equals("")||(Day.equals(""))){
                    Toast.makeText(ViewTimetable.this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
                }else {

                    GetMatchData();
                }

            }
        });
    }

    private void GetMatchData() {

        Section = editTextSection.getText().toString().trim();
        Day = editTextDay.getText().toString().trim();

        mProgressDialog = new ProgressDialog(ViewTimetable.this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage(getString(R.string.progress_detail));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgress(0);
        mProgressDialog.setProgressNumberFormat(null);
        mProgressDialog.setProgressPercentFormat(null);
        mProgressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, config9.MATCHDATA_URL,
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
                        Toast.makeText(ViewTimetable.this, ""+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_Section, Section);
                map.put(KEY_Day, Day);

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
            JSONArray result = jsonObject.getJSONArray(config9.JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String First = jo.getString(config9.KEY_First);
                String Second = jo.getString(config9.KEY_Second);
                String Third = jo.getString(config9.KEY_Third);
                String Fourth = jo.getString(config9.KEY_Fourth);
                String Fifth = jo.getString(config9.KEY_Fifth);
                String Sixth = jo.getString(config9.KEY_Sixth);
                String Seventh = jo.getString(config9.KEY_Seventh);
                String Eighth = jo.getString(config9.KEY_Eighth);
                String Ninth = jo.getString(config9.KEY_Ninth);





                final HashMap<String, String> employees = new HashMap<>();
                employees.put(config9.KEY_First, "8:35 - 9:25 = " + First);
                employees.put(config9.KEY_Second, "9:30 - 10:20 = " + Second);
                employees.put(config9.KEY_Third, "10:25 - 11:15 = " +Third);
                employees.put(config9.KEY_Fourth, "11:20 - 12:10 = " +Fourth);
                employees.put(config9.KEY_Fifth, "12:15 - 1:05 = " +Fifth);
                employees.put(config9.KEY_Sixth, "1:10 - 2:00 = " +Sixth);
                employees.put(config9.KEY_Seventh, "2:00 - 2:55 = " +Seventh);
                employees.put(config9.KEY_Eighth, "3:00 - 3:50 = " +Eighth);
                employees.put(config9.KEY_Ninth, "3:55 - 4:40 = " +Ninth);



                list.add(employees);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                ViewTimetable.this, list, R.layout.list_item5,
                new String[]{config9.KEY_First,config9.KEY_Second, config9.KEY_Third, config9.KEY_Fourth,config9.KEY_Fifth,config9.KEY_Sixth,config9.KEY_Seventh,config9.KEY_Eighth,config9.KEY_Ninth},
                new int[]{R.id.first,R.id.second,  R.id.third, R.id.fourth,R.id.fifth, R.id.sixth, R.id.seventh, R.id.eighth,R.id.ninth});

        listview.setAdapter(adapter);

    }


}