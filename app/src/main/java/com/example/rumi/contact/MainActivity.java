package com.example.rumi.contact;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    ImageButton img1, img2, img3, img4;
    EditText edit1, edit2, edit3, edit4;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    private ProgressDialog loading, hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        boolean mboolean = false;
        SharedPreferences settings = getSharedPreferences("PREFS_NAME", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        ConnectionDetector cd = new ConnectionDetector(this);
        if (!mboolean) {
            if (cd.isConnected()) {
                //loading = ProgressDialog.show(this, "Please wait...", "Loading Data...", false, false);
                Intent i = new Intent(this, search.class);
                startActivity(i);
                //Intent i = new Intent(this, search.class);
                //startActivityForResult(i, 1);
                //loading.dismiss();
                settings = getSharedPreferences("PREFS_NAME", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("FIRST_RUN", true);
                editor.commit();
            } else {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Please Check You Internet Connection!!!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
            }
        }
        //Intent i = new Intent(this, search.class);
        //startActivityForResult(i, 1);
        img1 = (ImageButton) findViewById(R.id.imageButton1);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);
        img4 = (ImageButton) findViewById(R.id.imageButton4);
        edit1 = (EditText) findViewById(R.id.eidt1);
        edit2 = (EditText) findViewById(R.id.eidt2);
        edit3 = (EditText) findViewById(R.id.eidt3);
        edit4 = (EditText) findViewById(R.id.eidt4);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        edit1.setOnClickListener(this);
        edit2.setOnClickListener(this);
        edit3.setOnClickListener(this);
        edit4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton1 || v.getId() == R.id.eidt1) {
            Intent intent = new Intent(this, university.class);
            startActivity(intent);

        } else if (v.getId() == R.id.imageButton2 || v.getId() == R.id.eidt2) {
            Intent intent = new Intent(this, blood_group.class);
            startActivity(intent);
        } else if (v.getId() == R.id.imageButton3 || v.getId() == R.id.eidt3) {
            Intent intent = new Intent(this, dept.class);
            startActivity(intent);
        } else if (v.getId() == R.id.imageButton4 || v.getId() == R.id.eidt4) {
            Intent intent = new Intent(this, districts.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuSearch1) {
            Intent i = new Intent(this, search_list.class);
            startActivity(i);
            return true;
        }
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

        if (id == R.id.nav_update) {
            ConnectionDetector cd = new ConnectionDetector(this);
            if (cd.isConnected()) {
                startActivity(new Intent(this, search.class));
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            }else{
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
                a_builder.setMessage("Please Check You Internet Connection!!!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("Alert !!!");
                alert.show();
            }

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(this, search_list.class));
        }
        else if (id == R.id.nav_setting) {
            Toast.makeText(this, "Blank", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            //String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));


        }
        else if (id == R.id.nav_send) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            //String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,"");
            startActivity(Intent.createChooser(sharingIntent, "Send Via"));
        }
        else if (id == R.id.nav_credits) {
            startActivity(new Intent(this, about.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
