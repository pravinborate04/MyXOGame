package com.pravin.myxogame;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

/**
 * Created by Pravin Borate on 23/5/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("message","received");

//        Log.e("chech",remoteMessage.getNotification().getBody());
        Intent intent1=new Intent("MY_RECEIVER");
        intent1.putExtra("check", remoteMessage.getData().get("title"));
        sendBroadcast(intent1);

    }
}
