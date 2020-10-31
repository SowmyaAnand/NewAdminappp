package com.dailyestoreapp.adminapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.dailyestoreapp.adminapp.Fragment1;
import com.dailyestoreapp.adminapp.Fragment2;
import com.dailyestoreapp.adminapp.Fragment4;
import com.dailyestoreapp.adminapp.R;
import com.dailyestoreapp.adminapp.SectionsPagerAdapter;
import com.dailyestoreapp.adminapp.fragment3;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentActivity myContext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(),getChildFragmentManager(),);
//        sectionsPagerAdapter.addFragment(new Fragment1(),"Home");
//        sectionsPagerAdapter.addFragment(new Fragment4(),"Offers");
//        sectionsPagerAdapter.addFragment(new fragment3(),"Contact");
//        sectionsPagerAdapter.addFragment(new Fragment4(),"My Account");
//        sectionsPagerAdapter.addFragment(new fragment3(),"Home");
//        sectionsPagerAdapter.addFragment(new fragment3(),"Offers");
//        sectionsPagerAdapter.addFragment(new Fragment4(),"Contact");
//        sectionsPagerAdapter.addFragment(new fragment3(),"My Account");
//        ViewPager viewPager =root.findViewById(R.id.view_pager2);
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = root.findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);

        return root;
    }
}
