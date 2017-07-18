package com.example.admin.rv;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> ids = new ArrayList<>();

    RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStudents = (RecyclerView) findViewById(R.id.rvStudents);

//        names.add("Vidisha");
//        names.add("Student X");
//        names.add("Student Y");
//        names.add("Student Z");
//
//        ids.add("1234");
//        ids.add("1235");
//        ids.add("1236");
//        ids.add("1237");

        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);

        rvStudents.setLayoutManager(lm);

//        StudentData adapter = new StudentData(MainActivity.this,names,ids);
//
//        rvStudents.setAdapter(adapter);

        String s = ReadFromfile("moviesData.json", MainActivity.this);

        converStringToJSON(s);
    }

    public void converStringToJSON(String s){

        try {
            JSONObject mainObject = new JSONObject(s);

            JSONObject authorObject = mainObject.getJSONObject("authorData");

            String name = authorObject.getString("name");
            String company = authorObject.getString("company");
            String website = authorObject.getString("website");

            Log.d("MainActivity",name + "\n" + company + "\n" + website);

            JSONObject movieObject = mainObject.getJSONObject("movieData");

            JSONArray movieArray = movieObject.getJSONArray("moviesList");

            for(int i = 0 ; i<movieArray.length(); i++){

                JSONObject currentObject = movieArray.getJSONObject(i);
                String movieName = currentObject.getString("movieName");
                String year = currentObject.getString("year");
                String rating = currentObject.getString("rating");

                names.add(movieName);
                ids.add(year);

            }

            StudentData adapter = new StudentData(MainActivity.this,names,ids);
            rvStudents.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String ReadFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }
}
