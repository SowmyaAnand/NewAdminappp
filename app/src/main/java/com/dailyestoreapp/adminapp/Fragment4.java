package com.dailyestoreapp.adminapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment4 extends Fragment   {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
private String tag = "fragment4";
    SearchView sr;


private Integer selectedSubCategoryNo;
    int start = 0,limit = 3;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ImageView refresh;
    ACProgressFlower dialog;
    ProgressBar progressBar;
    ArrayList<String> lts = new ArrayList<String>();
  //  ArrayList Images_sub = new ArrayList<>(Arrays.asList(R.drawable.h1,R.drawable.h2, R.drawable.h1, R.drawable.h2, R.drawable.h1));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.home,R.drawable.home, R.drawable.home, R.drawable.home, R.drawable.home,R.drawable.home));
    ArrayList personNames = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));
    ArrayList<String> Images_sub = new ArrayList<>();
    ArrayList<String> Sub_categories = new ArrayList<>();
    ArrayList<String> item_image = new ArrayList<>();
    ArrayList<String> Item_categories = new ArrayList<>();
    ArrayList<String> Item_categories_offer_desc = new ArrayList<>();
//    ArrayList<Integer> Item_Quantity = new ArrayList<>();
ArrayList<String> Item_Quantity = new ArrayList<>();
    ArrayList<Integer> Item_Price = new ArrayList<>();
    ArrayList<Integer> Sub_categories_id = new ArrayList<>();
    ArrayList<Integer> item_id = new ArrayList<>();
    ArrayList<Integer> item_id_offer = new ArrayList<>();
    ArrayList<Integer> item_id_status = new ArrayList<>();
    ArrayList<String> original_Item_categories_lts = new ArrayList<>();
    ArrayList<String> item_image_lts = new ArrayList<>();
    ArrayList<String> Item_Quantity_lts = new ArrayList<>();
    ArrayList<Integer> Item_Price_lts = new ArrayList<>();
    ArrayList<Integer> item_id_lts = new ArrayList<>();
    ArrayList<Integer> item_id_status_lts = new ArrayList<>();
    ArrayList<String> Item_categories_offer_desc_lts = new ArrayList<>();
    ArrayList<Integer> item_id_offer_lts = new ArrayList<>();
    ArrayList<String> original_item_image_lts = new ArrayList<>();
    ArrayList<String> original_Item_Quantity_lts = new ArrayList<>();
    ArrayList<Integer> original_Item_Price_lts = new ArrayList<>();
    ArrayList<Integer> original_item_id_lts = new ArrayList<>();
    ArrayList<Integer> original_item_id_status_lts = new ArrayList<>();
    ArrayList<String> original_Item_categories_offer_desc_lts = new ArrayList<>();
    ArrayList<Integer> original_item_id_offer_lts = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("farg4ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Offers_ItemAdapter customAdapter_offers;
    NestedScrollView nestedScrollViewItemsbasedonsubCategory;
    test customadapter2_offers;
 private static String cat_selected ;
    private static Integer cat_number ;
    public Fragment4() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1,String param2) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putString("category", param1);
        fragment.setArguments(args);
        return fragment;
    }

public void pageselect(String param)
{
    cat_selected=param;

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
    { int cat_id = 0;
        Bundle bundle = this.getArguments();
        if (bundle != null) {
           cat_id  = bundle.getInt("category");
        }
//        dialog = new ACProgressFlower.Builder(getContext())
//                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
//                .themeColor(Color.WHITE)
//                .borderPadding(1)
//
//                .fadeColor(Color.DKGRAY).build();
//        dialog.show();
        Log.e("fragment2","Inside Activate"+cat_number+cat_selected);
        int type =1;
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
        Call<ListCategoryResponse> call = mainInterface.SubCategory(cat_id);
        call.enqueue(new Callback<ListCategoryResponse>() {
            @Override
            public void onResponse(Call<ListCategoryResponse> call, retrofit2.Response<ListCategoryResponse> response) {
                String res= new GsonBuilder().setPrettyPrinting().create().toJson(response.body().getResponsedata());
                JsonObject obj = new JsonParser().parse(res).getAsJsonObject();
                try {
                    JSONObject jo2 = new JSONObject(obj.toString());
                    JSONArray categoriesarray = jo2.getJSONArray("data");
                    Log.e("Fragment4","subcategories="+jo2);

                    for(int i=0; i<categoriesarray.length(); i++)
                    {
                        JSONObject j1= categoriesarray.getJSONObject(i);
                        String sub_name = j1.getString("subName");
                        String sub_item =j1.getString("subItemImage");
                        if(!Sub_categories.contains(sub_name))
                        {
                            Sub_categories.add(sub_name);
                            Images_sub.add(sub_item);
                            int sub_Cat_id = Integer.parseInt(j1.getString("subId"));
                            Sub_categories_id.add(sub_Cat_id);

                        }

                    }

                    customadapter2_offers.notifyDataSetChanged();
                    Log.e(tag,"sub_cat inside Activate"+Sub_categories);
//                    dialog.dismiss();
                    selectedSubCategoryNo=1;
                   ItemsList(Sub_categories_id.get(0),start,limit);


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
    private void ItemsList(Integer subId ,int st,int lmt)
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
                String success = response.body().getResponsedata().getSuccess();
                dialog.dismiss();
                Log.e("frag4","success="+success);
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
                    item_image.clear();
                    Item_Quantity.clear();
                    Item_Price.clear();
                    item_id.clear();
                    item_id_status.clear();
                    Item_categories_offer_desc.clear();
                    item_id_offer.clear();
                    original_Item_categories_lts.clear();
                    original_Item_Quantity_lts.clear();
                    original_Item_Price_lts.clear();
                    original_item_id_lts.clear();
                    original_item_id_status_lts.clear();
                    original_item_image_lts.clear();
                    original_Item_categories_offer_desc_lts.clear();
                    original_item_id_offer_lts.clear();
                    if(success.equals("1"))
                    {
                        int data_length = response.body().getResponsedata().getData().size();



                    for(int i=0; i<data_length; i++)
                    {
//                        JSONObject j1= categoriesarray.getJSONObject(i);
//                        String item_name = j1.getString("itemName");
                        String item_name = response.body().getResponsedata().getData().get(i).getItemName();
                        int it_id = Integer.parseInt(response.body().getResponsedata().getData().get(i).getItemId());

                        String item_quant = response.body().getResponsedata().getData().get(i).getQuantity();
                        Integer item_price = Integer.valueOf(response.body().getResponsedata().getData().get(i).getPrice());
                        Integer item_status = Integer.valueOf(response.body().getResponsedata().getData().get(i).getStatus());
                        String imageurl = response.body().getResponsedata().getData().get(i).getImage();
                        Integer offer_percent =Integer.valueOf(response.body().getResponsedata().getData().get(i).getoffer());
                        String offer_desc = response.body().getResponsedata().getData().get(i).getofdescription();
                        String imageurl_total=url1+imageurl;
                        Log.e(tag,"imageurl"+url1+imageurl);
                        Item_categories.add(item_name);
                        Item_Quantity.add(item_quant);
                        Item_Price.add(item_price);
                        item_id.add(it_id);
                        item_id_status.add(item_status);
                        item_image.add(imageurl_total);
                        Item_categories_offer_desc.add(offer_desc);
                        item_id_offer.add(offer_percent);



                    }
                        original_Item_categories_lts.addAll(Item_categories);
                        original_Item_Quantity_lts.addAll(Item_Quantity);
                        original_Item_Price_lts.addAll(Item_Price);
                        original_item_id_lts.addAll(item_id);
                        original_item_id_status_lts.addAll(item_id_status);
                        original_item_image_lts.addAll(item_image);
                        original_Item_categories_offer_desc_lts.addAll(Item_categories_offer_desc);
                        original_item_id_offer_lts.addAll(item_id_offer);
                    customAdapter_offers.notifyDataSetChanged();
                    }
                    else {
                      //  Toast.makeText(getContext(),"No data found in next category",Toast.LENGTH_LONG).show();
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
        //nested recyclerview

        sr = (SearchView)rootView.findViewById(R.id.searchview);

        sr.setQueryHint("Type here to search( Based on sub category ) ");
        sr.setBackgroundColor(getResources().getColor(R.color.white));
        sr.setIconifiedByDefault(false);
        EditText txtSearch = ((EditText)sr.findViewById(androidx.appcompat.R.id.search_src_text));
      //  txtSearch.setHint("Type here to search");
        txtSearch.setHintTextColor(Color.LTGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.black));
        txtSearch.setTextSize(12);
        sr.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("text","textclose");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
Log.e("the text is","text"+newText);
//customAdapter_offers.filter(newText);
                lts.addAll(Item_categories);
                item_image_lts.addAll(item_image);
                Item_Quantity_lts.addAll(Item_Quantity);
                Item_Price_lts.addAll(Item_Price);
                item_id_lts.addAll(item_id);
                item_id_status_lts.addAll(item_id_status);
                Item_categories_offer_desc_lts.addAll(Item_categories_offer_desc);
                item_id_offer_lts.addAll(item_id_offer);
                Log.e("texting if", "persons=" + newText);
               // newText = newText.toLowerCase(Locale.getDefault());
                Log.e("texting if2", "persons=" + newText);
                Item_categories.clear();
                item_image.clear();
                Item_Quantity.clear();
                Item_Price.clear();
                item_id.clear();
                item_id_status.clear();
                Item_categories_offer_desc.clear();
                item_id_offer.clear();
                Iterator itr = Item_categories.iterator();
                if (newText.length() == 0) {
                    Log.e("texting if3", "persons=" + newText);
                    Item_categories.addAll(original_Item_categories_lts);
                    item_image.addAll(original_item_image_lts);
                    Item_Quantity.addAll(original_Item_Quantity_lts);
                    Item_Price.addAll(original_Item_Price_lts);
                    item_id.addAll(original_item_id_lts);
                    item_id_status.addAll(original_item_id_status_lts);
                    Item_categories_offer_desc.addAll(original_Item_categories_offer_desc_lts);
                    item_id_offer.addAll(original_item_id_offer_lts);
                } else {
                    for (int i = 0; i < lts.size(); i++) {
                        Log.e("texting else", "persons=" + lts.get(i));
                        String s = (String) lts.get(i); String s_img = item_image_lts.get(i);
                        String s_qnty =Item_Quantity_lts.get(i);
                        Integer s_price =Item_Price_lts.get(i);
                        Integer s_id =item_id_lts.get(i);
                        Integer s_status = item_id_status_lts.get(i);
                        String s_desc =Item_categories_offer_desc_lts.get(i);
                        Integer s_offer =item_id_offer_lts.get(i);

                        if (s.toLowerCase(Locale.getDefault()).contains(newText)) {
                            if(Item_categories.contains(s))
                            {

                            }
                            else
                            {
                                Item_categories.add(s);
                                item_image.add(s_img);
                                Item_Quantity.add(s_qnty);
                                Item_Price.add(s_price);
                                item_id.add(s_id);
                                item_id_status.add(s_status);
                                Item_categories_offer_desc.add(s_desc);
                                item_id_offer.add(s_offer);
                            }

                        }
                    }
                }
                Log.e("text", "persons=" + Item_categories);
                customAdapter_offers.notifyDataSetChanged();
                return true;
            }
        });
       //recyclerview
        itemlistingcategory_offers = (RecyclerView) rootView.findViewById(R.id.recyclerView_categories_offer);

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        Log.e(tag,"sub_cat inside oncreate"+Sub_categories);
        customadapter2_offers = new test(rootView.getContext(), Sub_categories,Images_sub,Sub_categories_id,communication);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        refresh = (ImageView)rootView.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subcategoryactivate();
            }
        });
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
            Item_categories.clear();
            item_image.clear();
            Item_Quantity.clear();
            Item_Price.clear();
            item_id.clear();
            item_id_status.clear();
            Item_categories_offer_desc.clear();
            item_id_offer.clear();
            selectedSubCategoryNo=name;
           ItemsList(name,0,3);
         //  customAdapter_offers.notifyDataSetChanged();


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
