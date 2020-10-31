package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CouponsList extends AppCompatActivity {
Button save ;
EditText cpName,cpPercent,cpDesc;
ACProgressFlower dialog;
EditText coupon_name,desc,offer_percent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("ADD COUPON");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        save = findViewById(R.id.save);
        cpName=findViewById(R.id.coupon_name);
        cpDesc=findViewById(R.id.coupon_description);
        cpPercent=findViewById(R.id.coupon_percentage);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpDescription = cpDesc.getText().toString();
                String cpPercentage = cpPercent.getText().toString();
                String cpNames = cpName.getText().toString();
             addCoupon(cpDescription,cpPercentage,cpNames);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("MAin", "Item selected =" + item.getItemId());
        switch (item.getItemId()) {
            case R.id.logout:
                Intent cart = new Intent(CouponsList.this, Login.class);
                startActivity(cart);
                return true;
            case R.id.account:
                Intent account1 = new Intent(CouponsList.this, MyAccount.class);
                startActivity(account1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class CustomDialogClass1 extends Dialog implements
            android.view.View.OnClickListener {

        public Context c;
        public Dialog d;
        public Button yes, no;
        String txt;
        public TextView textdisplayed;

        public CustomDialogClass1(Context a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;

        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.approve_dialog);
            yes = (Button) findViewById(R.id.btn_yes);

            textdisplayed=(TextView)findViewById(R.id.txt_dia);
            textdisplayed.setText("Coupon added successfully");
            yes.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_yes:
                    dismiss();
                    break;

                default:
                    break;
            }

        }
    }
    private void addCoupon(String description,String percent,String couponName)
    {
        dialog = new ACProgressFlower.Builder(CouponsList.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();


        String url = "http://dailyestoreapp.com/dailyestore/api/";

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        ResponseInterface1 mainInterface = retrofit.create(ResponseInterface1.class);
        Call<LoginResponse> call = mainInterface.addcoupon(description,percent,couponName);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                String success = response.body().getResponsedata().getSuccess();
                dialog.dismiss();
                Log.e("frag4","success="+success);

                try {

                    if(success.equals("1"))
                    {

                        CouponsList.CustomDialogClass1 cdd2=new CouponsList.CustomDialogClass1(CouponsList.this);
                           cdd2.show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CouponsList.this,"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(CouponsList.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
