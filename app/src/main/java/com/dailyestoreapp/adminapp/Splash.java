package com.dailyestoreapp.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Splash extends AppCompatActivity {

      private static int SPLASH_TIME_OUT = 1000;
  StringBuilder strbul  = new StringBuilder();
    StringBuilder ct  = new StringBuilder();
    StringBuilder ct_images  = new StringBuilder();
    ArrayList<String> categories = new ArrayList<>();
    ArrayList<String> categories_image = new ArrayList<>();
    List<String> cat_no = new ArrayList<String>();
    ArrayList<Integer> nums = new ArrayList<>();
    ACProgressFlower dialog;

    public static final String MY_PREFS_NAME = "AdminApp";
    private static String tag = "splash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseMessaging.getInstance().subscribeToTopic("allDevices");
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent homeintent = new Intent(Splash.this,Login.class);
                startActivity(homeintent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }


}
