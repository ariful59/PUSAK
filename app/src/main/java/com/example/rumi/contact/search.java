package com.example.rumi.contact;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class search extends AppCompatActivity {

    private EditText editTextId;
    private Button buttonGet;
    private TextView textViewResult;

    private ProgressDialog loading;
    private InputStream is;
    String line="",data[]={},data1[];
    String result="";
    ArrayAdapter<String>adapter;
    DataBaseHelper helper=new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        loading = ProgressDialog.show(this, "Please wait...", "Loading Data...", false, false);
        ListView list=(ListView)findViewById(R.id.listview);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        getData();
        helper.onUpgrade(this);
        loading.dismiss();
        //Intent returnIntent = new Intent();
        //setResult(MainActivity.RESULT_CANCELED, returnIntent);
        finish();

    }
    private boolean getData() {
        try {
            URL url = new URL(Config.DATA_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        } catch (Exception e) {
                return false;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();

        } catch (Exception e) {
            return false;
        }
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo=null;
            data=new String[ja.length()];
            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                data[i]=jo.getString("name");
                Contact c=new Contact();
                c.setemail(jo.getString("email"));
                c.setname(jo.getString("name"));
                c.setaddress(jo.getString("address"));
                c.setphone(jo.getString("phone_number"));
                c.setuniversity(jo.getString("university"));
                c.setdept(jo.getString("dept_name"));
                c.setyear(jo.getString("year"));
                c.setblood(jo.getString("blood_group"));
                helper.insertConact(c);
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
