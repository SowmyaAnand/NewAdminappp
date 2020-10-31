package com.dailyestoreapp.adminapp.ui.slideshow;

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

import com.dailyestoreapp.adminapp.OrdersAdapter;
import com.dailyestoreapp.adminapp.PendingNotificationAdapter;
import com.dailyestoreapp.adminapp.R;
import com.dailyestoreapp.adminapp.ui.gallery.GalleryViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class SlideshowFragment extends Fragment {
    RecyclerView recyclerView_offers;
    private GalleryViewModel galleryViewModel;
    PendingNotificationAdapter customAdapter_offers;
    ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.pendingrequests, container, false);
        recyclerView_offers = (RecyclerView) root.findViewById(R.id.itemrecycler_offers_pending);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_offers.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

//        customAdapter_offers = new PendingNotificationAdapter(root.getContext(), personNames_offers);
//        recyclerView_offers.setAdapter(customAdapter_offers);
        return root;
    }
}
