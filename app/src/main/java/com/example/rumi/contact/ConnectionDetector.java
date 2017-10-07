package com.example.rumi.contact;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Rumi on 7/14/2017.
 */

public class ConnectionDetector {
    Context context;
    public ConnectionDetector(Context context)
    {
        this.context=context;
    }
    public boolean isConnected(){
        ConnectivityManager conn=(ConnectivityManager)context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(conn!=null){
            NetworkInfo info=conn.getActiveNetworkInfo();
            if(info!=null){
                if(info.getState()== NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
