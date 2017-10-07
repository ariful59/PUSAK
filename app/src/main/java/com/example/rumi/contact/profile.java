package com.example.rumi.contact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Rumi on 7/13/2017.
 */

public class profile extends AppCompatActivity {
    DataBaseHelper dh=new DataBaseHelper(this);
    String data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        TextView text1=(TextView)findViewById(R.id.text1);
        TextView text2=(TextView)findViewById(R.id.text2);
        TextView text3=(TextView)findViewById(R.id.text3);
        TextView text5=(TextView)findViewById(R.id.text5);
        TextView text6=(TextView)findViewById(R.id.text6);
        TextView text7=(TextView)findViewById(R.id.text7);
        TextView text8=(TextView)findViewById(R.id.text8);
        TextView text10=(TextView)findViewById(R.id.text10);
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String text = mBundle.getString("name");
            //System.out.println(text);
            data=dh.details_profile(text);
            //System.out.println(text);
                System.out.println("profile print "+data);
                final String[] parts = data[0].split(",");
                    text1.setText(parts[0]);
                    text2.setText(parts[7]);
                    text3.setText(parts[5]);
                    text6.setText(parts[1]);
                    text5.setText(parts[4]);
                    text7.setText(parts[1]);
                    text8.setText(parts[2]);
                    text10.setText(parts[6]);
                    String ab=parts[3];
                    if(ab.equals("")) {
                        parts[3]="Not Found";
                    }else {
                        parts[3] = "0";
                        parts[3] += ab;
                    }
                    text6.setText(parts[3]);
                SpannableString content = new SpannableString(parts[1]);
                content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                text7.setText(content);

                ImageButton im= (ImageButton) findViewById(R.id.imageButton1);
                ImageButton im1= (ImageButton) findViewById(R.id.imageButton2);


                im.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent in=new Intent(Intent.ACTION_DIAL);
                        in.setData(Uri.parse("tel:"+parts[3]));
                        startActivity(in);
                    }
                });
                im1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent in=new Intent(Intent.ACTION_SENDTO);
                        in.setData(Uri.parse("smsto:"+parts[3]));
                        startActivity(in);
                    }
                });
                text7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(Intent.ACTION_SEND);
                        in.putExtra(Intent.EXTRA_EMAIL,new String[]{parts[1]});
                        in.setType("message/rfc822");
                        startActivity(Intent.createChooser(in,"Choice Email APP"));
                    }
                });



        }
    }
}
