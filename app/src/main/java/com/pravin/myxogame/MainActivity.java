package com.pravin.myxogame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.pravin.myxogame.retrofit.IWebServices;
import com.pravin.myxogame.retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img11, img12, img13,
            img21, img22, img23,
            img31, img32, img33;
    String user;
    int imageResourceId, opponet;
    FirebaseDatabase database;
    DatabaseReference userX, userO;
    String oppoentToken;
    FrameLayout view;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String check = intent.getStringExtra("check");
            Log.e("onReceive", check);
            view.setVisibility(View.GONE);
            switch (check) {
                case "11":
                    img11.setImageResource(opponet);
                    img11.setTag(opponet);
                    img11.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "12":
                    img12.setImageResource(opponet);
                    img12.setTag(opponet);
                    img12.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "13":
                    img13.setImageResource(opponet);
                    img13.setTag(opponet);
                    img13.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "21":
                    img21.setImageResource(opponet);
                    img21.setTag(opponet);
                    img21.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "22":
                    img22.setImageResource(opponet);
                    img22.setTag(opponet);
                    img22.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "23":
                    img23.setImageResource(opponet);
                    img23.setTag(opponet);
                    img23.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "31":
                    img31.setImageResource(opponet);
                    img31.setTag(opponet);
                    img31.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "32":
                    img32.setImageResource(opponet);
                    img32.setTag(opponet);
                    img32.setEnabled(false);
                    checkWinner(opponet);
                    break;
                case "33":
                    img33.setImageResource(opponet);
                    img33.setTag(opponet);
                    img33.setEnabled(false);
                    checkWinner(opponet);
                    break;
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receiver, new IntentFilter("MY_RECEIVER"));

        view=(FrameLayout)findViewById(R.id.view);
        database = FirebaseDatabase.getInstance();
        userX = database.getReference("user1");
        userO = database.getReference("user2");


        if (getIntent() != null) {
            user = getIntent().getStringExtra("user");
        }
        if (user.equals("o")) {
            imageResourceId = R.mipmap.ic_o;
            userX.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null){
                        oppoentToken = dataSnapshot.getValue().toString();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            imageResourceId = R.mipmap.ic_x;
            userO.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null){
                        oppoentToken = dataSnapshot.getValue().toString();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        if (imageResourceId == R.mipmap.ic_o) {
            opponet = R.mipmap.ic_x;
        } else {
            opponet = R.mipmap.ic_o;
        }

        img11 = (ImageView) findViewById(R.id.img11);
        img12 = (ImageView) findViewById(R.id.img12);
        img13 = (ImageView) findViewById(R.id.img13);

        img21 = (ImageView) findViewById(R.id.img21);
        img22 = (ImageView) findViewById(R.id.img22);
        img23 = (ImageView) findViewById(R.id.img23);

        img31 = (ImageView) findViewById(R.id.img31);
        img32 = (ImageView) findViewById(R.id.img32);
        img33 = (ImageView) findViewById(R.id.img33);

        img11.setOnClickListener(this);
        img12.setOnClickListener(this);
        img13.setOnClickListener(this);

        img21.setOnClickListener(this);
        img22.setOnClickListener(this);
        img23.setOnClickListener(this);

        img31.setOnClickListener(this);
        img32.setOnClickListener(this);
        img33.setOnClickListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("MY_RECEIVER"));
    }

    @Override
    public void onClick(View v) {
        Log.e("oppentToken",TextUtils.isEmpty(oppoentToken)+"");
        if(TextUtils.isEmpty(oppoentToken)){
            Toast.makeText(MainActivity.this, "Waiting for Opponent", Toast.LENGTH_SHORT).show();
            return;
        }
        view.setVisibility(View.VISIBLE);

        switch (v.getId()) {
            case R.id.img11:
                img11.setImageResource(imageResourceId);
                img11.setEnabled(false);
                img11.setTag(imageResourceId);
                checkWinner(imageResourceId);
                sendResult("11");
                break;
            case R.id.img12:
                img12.setImageResource(imageResourceId);
                img12.setEnabled(false);
                img12.setTag(imageResourceId);
                sendResult("12");
                checkWinner(imageResourceId);
                break;
            case R.id.img13:
                img13.setImageResource(imageResourceId);
                img13.setEnabled(false);
                img13.setTag(imageResourceId);
                sendResult("13");
                checkWinner(imageResourceId);
                break;
            case R.id.img21:
                img21.setImageResource(imageResourceId);
                img21.setEnabled(false);
                img21.setTag(imageResourceId);
                sendResult("21");
                checkWinner(imageResourceId);
                break;
            case R.id.img22:
                img22.setImageResource(imageResourceId);
                img22.setEnabled(false);
                img22.setTag(imageResourceId);
                sendResult("22");
                checkWinner(imageResourceId);
                break;
            case R.id.img23:
                img23.setImageResource(imageResourceId);
                img23.setEnabled(false);
                img23.setTag(imageResourceId);
                sendResult("23");
                checkWinner(imageResourceId);
                break;

            case R.id.img31:
                img31.setImageResource(imageResourceId);
                img31.setEnabled(false);
                img31.setTag(imageResourceId);
                sendResult("31");
                checkWinner(imageResourceId);
                break;
            case R.id.img32:
                img32.setImageResource(imageResourceId);
                img32.setEnabled(false);
                img32.setTag(imageResourceId);
                sendResult("32");
                checkWinner(imageResourceId);
                break;
            case R.id.img33:
                img33.setImageResource(imageResourceId);
                img33.setEnabled(false);
                img33.setTag(imageResourceId);
                sendResult("33");
                checkWinner(imageResourceId);
                break;

        }
    }


    public void sendResult(String result) {

        IWebServices services = RetrofitManager.getInstance().getClient().create(IWebServices.class);
        SendFcmModel model = new SendFcmModel();
        model.setTo(oppoentToken);
        SendFcmModel.DataEntity data = new SendFcmModel.DataEntity();
        data.setTitle(result);
        model.setData(data);
        Log.e("request",new Gson().toJson(model));

        services.sendMassage(model).enqueue(new Callback<SendFcmResponseModel>() {
            @Override
            public void onResponse(Call<SendFcmResponseModel> call, Response<SendFcmResponseModel> response) {
                Log.e("response",new Gson().toJson(response.body()));
                if(response.body()!=null && response.body().getSuccess()>0){
                }else {
                    Toast.makeText(MainActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
                    Log.e("fail","onSuccess");
                }
            }

            @Override
            public void onFailure(Call<SendFcmResponseModel> call, Throwable t) {
                Log.e("EROOR",t.getLocalizedMessage());
            }
        });
    }

    public void checkWinner(int user) {
        if (img11.getTag() != null && img12.getTag() != null && img13.getTag() != null) {
            if (img11.getTag().toString().equals(img12.getTag().toString()) && img11.getTag().toString().equals(img13.getTag().toString()) && img12.getTag().toString().equals(img13.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }


        if (img21.getTag() != null && img22.getTag() != null && img23.getTag() != null) {
            if (img21.getTag().toString().equals(img22.getTag().toString()) && img21.getTag().toString().equals(img23.getTag().toString()) && img22.getTag().toString().equals(img23.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img31.getTag() != null && img32.getTag() != null && img33.getTag() != null) {
            if (img31.getTag().toString().equals(img32.getTag().toString()) && img31.getTag().toString().equals(img33.getTag().toString()) && img32.getTag().toString().equals(img33.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img11.getTag() != null && img21.getTag() != null && img31.getTag() != null) {
            if (img11.getTag().toString().equals(img21.getTag().toString()) && img21.getTag().toString().equals(img31.getTag().toString()) && img11.getTag().toString().equals(img31.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img12.getTag() != null && img22.getTag() != null && img32.getTag() != null) {
            if (img12.getTag().toString().equals(img22.getTag().toString()) && img22.getTag().toString().equals(img32.getTag().toString()) && img12.getTag().toString().equals(img32.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img13.getTag() != null && img23.getTag() != null && img33.getTag() != null) {
            if (img13.getTag().toString().equals(img23.getTag().toString()) && img23.getTag().toString().equals(img33.getTag().toString()) && img13.getTag().toString().equals(img33.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }


        if (img11.getTag() != null && img22.getTag() != null && img33.getTag() != null) {
            if (img11.getTag().toString().equals(img22.getTag().toString()) && img22.getTag().toString().equals(img33.getTag().toString()) && img11.getTag().toString().equals(img33.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img13.getTag() != null && img22.getTag() != null && img31.getTag() != null) {
            if (img13.getTag().toString().equals(img22.getTag().toString()) && img22.getTag().toString().equals(img31.getTag().toString()) && img13.getTag().toString().equals(img31.getTag().toString())) {
                showWinnerAlertDialog(user);
            }
        }

        if (img11.getTag() != null && img12.getTag() != null && img13.getTag() != null &&
                img21.getTag() != null && img22.getTag() != null && img23.getTag() != null &&
                img31.getTag() != null && img32.getTag() != null && img33.getTag() != null) {
            showWinnerAlertDialog(0);
        }
    }


    public void showWinnerAlertDialog(int user){
        userX.removeValue();
        userO.removeValue();
        String userSign;
        if(user==R.mipmap.ic_o){
            userSign="O";
        }else {
            userSign="X";
        }
        if(user==0){
            userSign="Game Finished";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        if(user==0){
            builder.setTitle("XOGAME");
            builder.setMessage(userSign);
        }else {
            builder.setTitle("Winner");
            builder.setMessage(userSign+" won!");
        }

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        builder.show();
    }
}
