package com.example.rumi.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

/**
 * Created by Rumi on 7/15/2017.
 */

public class search_list extends AppCompatActivity {
    DataBaseHelper dh=new DataBaseHelper(this);
    ArrayAdapter<String> adapter;
    String data[];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni);
        ListView list=(ListView)findViewById(R.id.listview);
        data=dh.all_students();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            ListView list=(ListView)findViewById(R.id.listview);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String a = (String) list.getItemAtPosition(i);
                System.out.println(a);
                Intent intent = new Intent(search_list.this, profile.class);
                intent.putExtra("name",a);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
