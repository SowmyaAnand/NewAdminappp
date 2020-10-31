package com.dailyestoreapp.adminapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyestoreapp.adminapp.Offers_ItemAdapter;
import com.dailyestoreapp.adminapp.OrdersAdapter;
import com.dailyestoreapp.adminapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class GalleryFragment extends Fragment {
    RecyclerView recyclerView_offers;
    private GalleryViewModel galleryViewModel;
    OrdersAdapter customAdapter_offers;
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment2, container, false);
        recyclerView_offers = (RecyclerView) root.findViewById(R.id.itemrecycler_offers);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

       // customAdapter_offers = new OrdersAdapter(root.getContext(), personNames_offers);
       // recyclerView_offers.setAdapter(customAdapter_offers);

        return root;
    }
}
