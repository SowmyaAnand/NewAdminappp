package com.dailyestoreapp.adminapp.ui.Messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyestoreapp.adminapp.MessagesAdapter;
import com.dailyestoreapp.adminapp.OrdersAdapter;
import com.dailyestoreapp.adminapp.R;
import com.dailyestoreapp.adminapp.ui.gallery.GalleryViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class Messages  extends Fragment {
    RecyclerView recyclerView_messagelist;
    private MessagesViewModel messageViewModel;
    MessagesAdapter customAdapter_offers;
    ArrayList person__Name = new ArrayList();
    ArrayList person__mob = new ArrayList();
    ArrayList person__email= new ArrayList();
   // ArrayList personNames_offers = new ArrayList<>(Arrays.asList("ITEM1", "ITEM2", "ITEM3", "ITEM4", "ITEM5", "ITEM6", "ITEM7"));
   ArrayList personNames_offers = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        messageViewModel =
                ViewModelProviders.of(this).get(MessagesViewModel.class);
        View root = inflater.inflate(R.layout.messageslist, container, false);
        recyclerView_messagelist = (RecyclerView) root.findViewById(R.id.messages_list);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerView_messagelist.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter

        customAdapter_offers = new MessagesAdapter(root.getContext(), personNames_offers,person__email,person__mob);
        recyclerView_messagelist.setAdapter(customAdapter_offers);

        return root;
    }
}
