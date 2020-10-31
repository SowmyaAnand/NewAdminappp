package com.dailyestoreapp.adminapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dailyestoreapp.adminapp.ui.home.HomeViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    String category_selected;
   int category_selected_no;
private String tag ="HomeFragment";
    public static final String MY_PREFS_NAME = "AdminApp";

Fragment4 frag4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> categoriesHome = new ArrayList<>();

        ArrayList<Integer> categoriesHomeNo2 = new ArrayList<>();

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences shared = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String savedcatString = shared.getString("categories_new", "");
Log.e("homefragment","the catgeories shared preference are ="+savedcatString);
        String[] cats = savedcatString.split(",");//if spaces are uneven, use \\s+ instead of " "

       if(cats.length>0)
       {
           for (String ct : cats) {
               if(!(cats.equals("")||(cats.equals(null))))
               {
                   categoriesHome.add(ct);
               }

           }
       }

        //categoriesHome.addAll(set);





        SharedPreferences shared2 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedString = shared2.getString("categories_no_new", "");
        Log.e(tag,"numbers are"+savedString);
        String[] numbers = savedString.split(",");//if spaces are uneven, use \\s+ instead of " "
        Log.e(tag,"numbers are"+numbers);

            for (String number : numbers) {
                if(!(number.equals("")||(number.equals(null))))
                {
                    categoriesHomeNo2.add(Integer.valueOf(number));
                }


            }


        Log.e("cat_home","cat home num="+categoriesHomeNo2);
        category_selected=categoriesHome.get(0);



        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),categoriesHome);
       // frag4 = new Fragment4();
        for(int i = 0;i<categoriesHome.size();i++)
        {

            category_selected=categoriesHome.get(i);
            category_selected_no=categoriesHomeNo2.get(i);
            Bundle bundle = new Bundle();
                bundle.putInt("category", category_selected_no);
                Fragment4 mapFragment2 = new Fragment4();
                mapFragment2.setArguments(bundle);

                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));

                    sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));


//            if(category_selected.equals("Food"))
//            {
//
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("category", category_selected_no);
//                Fragment4 mapFragment2 = new Fragment4();
//                mapFragment2.setArguments(bundle);
//
//                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));
//
//                sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));
//            }
//           else if (i==0)  //api integrating in this
//            {
//                Bundle bundle = new Bundle();
//                bundle.putInt("category", category_selected_no);
//                Fragment4 mapFragment2 = new Fragment4();
//                mapFragment2.setArguments(bundle);
//
//
//                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));
//
//                sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));
//
//            }
//            else if(i==1)
//            {
////                Bundle bundle = new Bundle();
////                bundle.putString("category", category_selected);
////                Fragment2 mapFragment4 = new Fragment2(category_selected,categoriesHomeNo2.get(i));
////                mapFragment4.setArguments(bundle);
////
////
////                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));
////
////                sectionsPagerAdapter.addFragment(mapFragment4,categoriesHome.get(i));
//                Bundle bundle = new Bundle();
//                bundle.putInt("category", category_selected_no);
//                Fragment4 mapFragment2 = new Fragment4();
//                mapFragment2.setArguments(bundle);
//
//
//                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));
//
//                sectionsPagerAdapter.addFragment(mapFragment2,categoriesHome.get(i));
//
//            }
//            else
//            {
//                Bundle bundle = new Bundle();
//                bundle.putInt("category", category_selected_no);
//                Fragment2 mapFragment4 = new Fragment2(category_selected,categoriesHomeNo2.get(i));
//                mapFragment4.setArguments(bundle);
//
//
//                Log.e("tag","tag cat fragment4"+categoriesHome.get(i));
//
//                sectionsPagerAdapter.addFragment(mapFragment4,categoriesHome.get(i));
//            }

        }

        ViewPager viewPager =root.findViewById(R.id.view_pager2);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }



}
