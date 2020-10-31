package com.dailyestoreapp.adminapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment2 extends Fragment   {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String tag = "Fragment2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ACProgressFlower dialog;

    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.h1,R.drawable.h2, R.drawable.h1, R.drawable.h2, R.drawable.h1));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.home,R.drawable.home, R.drawable.home, R.drawable.home, R.drawable.home,R.drawable.home));
    ArrayList personNames = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));
    ArrayList<String> Sub_categories = new ArrayList<>();
    ArrayList<String> item_image = new ArrayList<>();
    ArrayList<String> Item_categories = new ArrayList<>();
    ArrayList<Integer> Item_Quantity = new ArrayList<>();
    ArrayList<Integer> Item_Price = new ArrayList<>();
    ArrayList<Integer> Sub_categories_id = new ArrayList<>();
    ArrayList<Integer> item_id = new ArrayList<>();
    ArrayList<Integer> item_id_offer = new ArrayList<>();
    ArrayList<String> Item_categories_offer_desc = new ArrayList<>();
    ArrayList<Integer> item_id_status = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Offers_ItemAdapter customAdapter_offers;
    test customadapter2_offers;
    private static String cat_selected ;
    private static Integer cat_number ;
    public Fragment2(String categoryy,Integer cat_no) {
        cat_selected=categoryy;
        cat_number=cat_no;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1,String param2) {
        Fragment2 fragment = new Fragment2(param1,1);
        Bundle args = new Bundle();
        args.putString("category", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public void pageselect(String param)
    {cat_selected=param;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            String st = getArguments().getString("category");


            Log.e("fragment2","oncreate"+cat_selected);
            Log.e("fragment","oncreate"+cat_number);
        }

    }
    //    public void pass(String ct) {
//        Log.e("paramsss","paramsss"+ct);
//        cat_selected=ct;
//        Log.e("paramsss","paramsss"+ cat_selected);
//    }
//    public void callOnlyService(){
////this.cat_selected=this.getArguments().getString("category");
//       // Log.e("frg","cat vlaue in callonly service"+this.cat_selected);
////        isCategory1AlreadyCalled = true;
////        new NotificationUtility(getActivity());
////        new MessageAPI().execute();
////        //   messageAPI();
////        new GetDataListAPI().execute(categoryId, fromDate, toDate);
//
//
//
//    }
    public void change()
    {  Log.e("fragment2","change"+this.cat_selected);
        // personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

    }
    private void subcategoryactivate()
    {
//        dialog = new ACProgressFlower.Builder(getContext())
//                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                .themeColor(Color.WHITE)
//                .borderPadding(1)
//
//                .fadeColor(Color.DKGRAY).build();
//        dialog.show();
        Log.e("fragment2","Inside Activate"+cat_number);
        int type = cat_number;
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
        Call<ListCategoryResponse> call = mainInterface.SubCategory(cat_number);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
                    JSONObject jo2 = new JSONObject(obj.toString());
                    JSONArray categoriesarray = jo2.getJSONArray("data");
                    Log.e("Fragment2","subcategories="+jo2);

                    for(int i=0; i<categoriesarray.length(); i++)
                    {
                        JSONObject j1= categoriesarray.getJSONObject(i);
                        String sub_name = j1.getString("subName");
                        if(!Sub_categories.contains(sub_name))
                        {
                            Sub_categories.add(sub_name);
                            int sub_Cat_id = Integer.parseInt(j1.getString("subId"));
                            Sub_categories_id.add(sub_Cat_id);

                        }

                    }

                    customadapter2_offers.notifyDataSetChanged();
                    Log.e(tag,"sub_cat inside Activate"+Sub_categories);
//                    dialog.dismiss();
                    ItemsList(1);


                    //personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "frag4ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {

            }
        });


    }
    private void ItemsList(Integer subId)
    {
        dialog = new ACProgressFlower.Builder(getContext())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .borderPadding(1)

                .fadeColor(Color.DKGRAY).build();
        dialog.show();


        String url = "http://dailyestoreapp.com/dailyestore/api/";
        final String url1 = "http://dailyestoreapp.com/dailyestore/";
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
        Call<ListCategoryResponse> call = mainInterface.Items(subId);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                ListCategoryResponse listCategoryResponseobject = response.body();
                int success = Integer.parseInt(response.body().getResponsedata().getSuccess());
                dialog.dismiss();
//                if(success==1)
//                {
//                    int data_length = response.body().getResponsedata().getData().size();
//                    String item_name = response.body().getResponsedata().getData().get(1).getItemName();
//                }
//                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
//                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
//                dialog.dismiss();
                try {
//                    JSONObject jo2 = new JSONObject(obj.toString());
//                    JSONArray categoriesarray = jo2.getJSONArray("data");
//                    Log.e(tag,"items="+jo2);
                    Item_categories.clear();
                    if(success==1)
                    {
                        int data_length = response.body().getResponsedata().getData().size();



                        for(int i=0; i<data_length; i++)
                        {
//                        JSONObject j1= categoriesarray.getJSONObject(i);
//                        String item_name = j1.getString("itemName");
                            String item_name = response.body().getResponsedata().getData().get(i).getItemName();
                            if(!Item_categories.contains(item_name))
                            {
//                            Integer item_quant = Integer.valueOf(j1.getString("quantity"));
//                            Integer item_price = Integer.valueOf(j1.getString("price"));
//                            Integer item_status = Integer.valueOf(j1.getString("price"));
//                            int it_id = Integer.parseInt(j1.getString("itemId"));
                                Integer item_quant = Integer.valueOf(response.body().getResponsedata().getData().get(i).getQuantity());
                                Integer item_price = Integer.valueOf(response.body().getResponsedata().getData().get(i).getPrice());
                                Integer item_status = Integer.valueOf(response.body().getResponsedata().getData().get(i).getStatus());
                                int it_id = Integer.parseInt(response.body().getResponsedata().getData().get(i).getItemId());
                                // String imageurl = response.body().getResponsedata().getData().get(i).getImage();
                                String imageurl=url1+"uploads/items/1.jpeg";
                                Log.e(tag,"imageurl"+url1+imageurl);
                                Item_categories.add(item_name);
                                Item_Quantity.add(item_quant);
                                Item_Price.add(item_price);
                                item_id.add(it_id);
                                item_id_status.add(item_status);
                                item_image.add(imageurl);

                            }

                        }

                        customAdapter_offers.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(getContext(),"No Data found",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),"something went wrong",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ListCategoryResponse> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        change();
        subcategoryactivate();
        Log.e(tag,"onactivityview called");
        if (getArguments() != null) {
            mParam1 = getArguments().getString("category");


            Log.e("fragment2","**"+this.cat_selected);
            Log.e("fragment2","**"+this.cat_number);
        }
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        itemlistingcategory_offers = (RecyclerView) rootView.findViewById(R.id.recyclerView_categories_offer);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        Log.e(tag,"sub_cat inside oncreate"+Sub_categories);
        customadapter2_offers = new test(rootView.getContext(), Sub_categories,Images_offers,Sub_categories_id,communication);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        recyclerView_offers = (RecyclerView) rootView.findViewById(R.id.itemrecycler_offers);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new Offers_ItemAdapter(rootView.getContext(), Item_categories,item_image,Item_Quantity,Item_Price,item_id,item_id_status,Item_categories_offer_desc,item_id_offer);
        recyclerView_offers.setAdapter(customAdapter_offers);
        // GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        // gridview.setAdapter(new ImageAdapter(rootView.getContext()));
        return rootView;
    }
    categorySubcategoryCommunicaion communication=new categorySubcategoryCommunicaion() {
        @Override
        public void respond(Integer name) {
            Log.e(tag," sub name is"+name);
            Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();
            Item_categories.clear();
            ItemsList(name);
            customAdapter_offers.notifyDataSetChanged();


        }

    };
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            cat_selected = getArguments().getString("category");
        }
        Log.e("frag","onactivitycreated  category from pass"+ cat_selected);
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString("category");
//
//            Log.e("fragment","oncreateview called"+mParam1);
//        }

    }




}
