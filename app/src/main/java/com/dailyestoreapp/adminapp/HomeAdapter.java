package com.dailyestoreapp.adminapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();
    public HomeAdapter(Context context, FragmentManager fm ,ArrayList<String> cat) {
        super(fm);



    }
    @NonNull


       /* @Override
        public int getItemPosition(@NonNull Object object) {
            HomeFragmentData homeFragmentData = (HomeFragmentData)object;
            if(homeFragmentData!=null && isRestarted){
                isRestarted = false;
                homeFragmentData.restartCallService();
            }

            return super.getItemPosition(object);
        }*/

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    public void addFrag(Fragment activityFragment, String categoryName) {
        fragmentList.add(activityFragment);
        fragmentTitleList.add(categoryName);
    }
}
