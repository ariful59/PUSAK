package com.example.rumi.contact;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;

/**
 * Created by Rumi on 7/13/2017.
 */

public class university_list extends AppCompatActivity{
    DataBaseHelper dh=new DataBaseHelper(this);
    ArrayAdapter<String>adapter;
    String data[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String text = mBundle.getString("name");
            ListView list=(ListView)findViewById(R.id.listview);
            data=dh.student_list(text);
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                ListView list=(ListView)findViewById(R.id.listview);
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    String a = (String) list.getItemAtPosition(i);
                    System.out.println(a);
                    Intent intent = new Intent(university_list.this, profile.class);
                    intent.putExtra("name",a);
                    startActivity(intent);
                }
            });

        }
    }
}
