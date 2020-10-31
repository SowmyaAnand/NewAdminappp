package com.dailyestoreapp.adminapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class EditCategories extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Integer> categoriescatno_edit = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList Images_offers = new ArrayList<>(Arrays.asList(R.drawable.newvegetable,R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable, R.drawable.fried_chicken_clip_art_8_1, R.drawable.newvegetable));
    ArrayList Images_images = new ArrayList<>(Arrays.asList(R.drawable.wp2375838_1,R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1, R.drawable.wp2375838_1));
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    RecyclerView categories;
    LinearLayoutManager linearLayoutManager_offers,linearLayoutManager2_offers;
    EditCategoriesAdapter customAdapter_offers;
    public static final String MY_PREFS_NAME = "AdminApp";
    public EditCategories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCategories.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCategories newInstance(String param1, String param2) {
        EditCategories fragment = new EditCategories();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

   

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.editcategories, container, false);
        ArrayList<String> categoriesEditCategies = new ArrayList<>();
        ArrayList<String> categoriesEditCategies_image = new ArrayList<>();

        SharedPreferences shared = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedcatString = shared.getString("categories_new", "");
        String[] cats = savedcatString.split(",");//if spaces are uneven, use \\s+ instead of " "
        for (String ct : cats) {
            if(!(ct.equals("")||ct.equals(null)))
            {
                categoriesEditCategies.add(ct);
            }

        }

        SharedPreferences shared4 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedcatString_image = shared4.getString("categories_image_new", "");
        String[] cats_image = savedcatString_image.split(",");//if spaces are uneven, use \\s+ instead of " "
        for (String ct : cats_image) {
            if(!(ct.equals("")||ct.equals(null)))
            {
                categoriesEditCategies_image.add(ct);
            }

        }

        SharedPreferences shared2 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String savedString = shared2.getString("categories_no_new", "");
        Log.e("editcategories","the categories are images arae "+categoriesEditCategies_image);
        String[] numbers = savedString.split(",");//if spaces are uneven, use \\s+ instead of " "
        for (String number : numbers) {
            if(!(numbers.equals("")||numbers.equals(null)))
            {
                categoriescatno_edit.add(Integer.valueOf(number));
            }

    }
       //uncomment in future for editcategory rimage
//        SharedPreferences shared2 = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        Set<String> set4 = shared.getStringSet("categories_image", null);
//        categoriesEditCategies.addAll(set4);
        //second recyclerview

        categories = (RecyclerView) rootView.findViewById(R.id.categorieslist);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        categories.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new EditCategoriesAdapter(rootView.getContext(), categoriesEditCategies,categoriesEditCategies_image,categoriescatno_edit);
        categories.setAdapter(customAdapter_offers);
        // GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        // gridview.setAdapter(new ImageAdapter(rootView.getContext()));
        return rootView;
    }
}
