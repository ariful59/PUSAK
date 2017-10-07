package com.example.rumi.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Rumi on 7/11/2017.
 */

public class dept extends AppCompatActivity{
    String data[];
    DataBaseHelper dh=new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        data=dh.dept_search();
        final ListView list=(ListView)findViewById(R.id.listview);
        HashMap<String, String> nameAddresses = new HashMap<>();
        for(int i=0;i<data.length;i++) {
            int a=dh.total_dept(data[i]);
            String ab=a+" Students\n";
            nameAddresses.put(data[i],ab);
        }

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_item,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});


        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        list.setAdapter(adapter);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            HashMap<String, String> a = new HashMap<>();
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                a = ((HashMap<String, String>) list.getItemAtPosition(i));
                String res=a.get("First Line");
                //System.out.println(res);
                Intent intent = new Intent(dept.this, dept_list.class);
                intent.putExtra("name",res);
                startActivity(intent);
            }
        });
    }
}
