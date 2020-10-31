package com.dailyestoreapp.adminapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    @StringRes
  //  private static final int[] TAB_TITLES = new int[]{R.string.category1, R.string.category4, R.string.category7, R.string.category2,R.string.category5, R.string.category6, R.string.category3, R.string.category8,R.string.category9};
    private static  int[] TAB_TITLES;

    private final Context mContext;
ArrayList<String> text = new ArrayList<>();


    public SectionsPagerAdapter(Context context, FragmentManager fm ,ArrayList<String> cat) {
        super(fm);
        mContext = context;
this.text=cat;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return mFragmentTitleList.get(position);
    }
    @Override
    public int getCount() {
        return text.size();
    }
    public void addFragment(Fragment fragment, String title) {
Log.e("add","add");
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);


    }


}
