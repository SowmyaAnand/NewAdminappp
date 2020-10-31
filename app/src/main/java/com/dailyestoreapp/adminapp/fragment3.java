package com.dailyestoreapp.adminapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class fragment3 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    ArrayList<Integer> item_id = new ArrayList<>();
    private String mParam2;
    ArrayList<String> Item_categories_offer_desc = new ArrayList<>();
    ArrayList<Integer> Item_Quantity = new ArrayList<>();
    ArrayList<Integer> Item_Price = new ArrayList<>();
    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.newvegetable,R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable, R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList<String> item_image = new ArrayList<>();
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("frag3ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    RecyclerView recyclerView_offers,itemlistingcategory_offers;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    Offers_ItemAdapter customAdapter_offers;
    ArrayList<Integer> item_id_offer = new ArrayList<>();
    OffersListingsubcategoryadapter customadapter2_offers;
    private static String ct;
    ArrayList<Integer> item_id_status = new ArrayList<>();
    public fragment3(String nm) {
        ct=nm;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment3 newInstance(String param1, String param2) {
        fragment3 fragment = new fragment3("test");
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    public void change()
    { Log.e("fragment3","change"+this.ct);
        personNames_offers = new ArrayList<>(Arrays.asList("frag3ITEM1", "frag3ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("fragment3","oncreateview called"+this.ct);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
change();
        itemlistingcategory_offers = (RecyclerView) rootView.findViewById(R.id.recyclerView_categories_offer);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager2_offers = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        itemlistingcategory_offers.setLayoutManager(linearLayoutManager2_offers);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        customadapter2_offers = new OffersListingsubcategoryadapter(rootView.getContext(), personNames_offers,Images_offers);
        itemlistingcategory_offers.setAdapter(customadapter2_offers);
        //second recyclerview
        recyclerView_offers = (RecyclerView) rootView.findViewById(R.id.itemrecycler_offers);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new Offers_ItemAdapter(rootView.getContext(), personNames_offers,item_image,Item_Quantity,Item_Price,item_id,item_id_status,Item_categories_offer_desc,item_id_offer);
        recyclerView_offers.setAdapter(customAdapter_offers);
        // GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        // gridview.setAdapter(new ImageAdapter(rootView.getContext()));
        return rootView;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.e("fragment3", "onactivitycreated  category from pass" + this.ct);
//        if (getArguments() != null) {
//            cat = getArguments().getString("category");
//            Log.e("frag", "onactivitycreated  category from pass" + cat);
//        }

    }
}


