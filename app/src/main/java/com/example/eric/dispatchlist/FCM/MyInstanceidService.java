package com.example.eric.dispatchlist.FCM;

import android.util.Log;

import com.example.eric.dispatchlist.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by Student on 2018/2/6.
 */

public class MyInstanceidService extends FirebaseInstanceIdService {
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Tag", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    public void sendRegistrationToServer(String refreshedToken) {
        HashMap<String,String> tokenlist = new HashMap<>();
        tokenlist.put(MainActivity.user,refreshedToken);
        myRef = database.getReference("token");

        Gson gson = new Gson();
        String data = gson.toJson(tokenlist);

        myRef.setValue(data);
    }
}
