package com.pravin.myxogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class SelectUserActivity extends AppCompatActivity {

    ImageView imgX,imgO;
    FirebaseDatabase database;
    DatabaseReference user1,user2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_user);

        database = FirebaseDatabase.getInstance();
        user1 = database.getReference("user1");
        user2 = database.getReference("user2");
        imgX=(ImageView)findViewById(R.id.imgX);
        imgO=(ImageView)findViewById(R.id.imgO);




        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user1.removeValue();
                Intent intent=new Intent(SelectUserActivity.this,MainActivity.class);
                intent.putExtra("user","x");
                startActivity(intent);
                user1.setValue(FirebaseInstanceId.getInstance().getToken());
            }
        });


        imgO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user2.removeValue();
                Intent intent=new Intent(SelectUserActivity.this,MainActivity.class);
                intent.putExtra("user","o");
                user2.setValue(FirebaseInstanceId.getInstance().getToken());
                startActivity(intent);
            }
        });
    }
}
