package com.survey.worklake.addmission;

import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 2/3/2016.
 */
//Extending FragmentStatePagerAdapter


class Pagerlist extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {


    public List<Fragment> mfragments = new ArrayList<Fragment>();
    private ViewPager pager;
    private TabLayout tabLayout;

    public Pagerlist(FragmentManager fm,ViewPager pager,TabLayout tabLayout) {
        super(fm);
        this.pager = pager;
        pager.setOnPageChangeListener(this);
        this.tabLayout = tabLayout;
        mfragments.add(PersonalFragment.newInstance());
    }



    @Override
    public int getCount() {

        return mfragments.size();
    }

    public void addTab(Fragment fragment) {
        mfragments.add(fragment);
        notifyDataSetChanged();
        tabLayout.setupWithViewPager(pager);
        for(int i = 0 ; i < mfragments.size()  ; i++) {
            switch(i) {
                case 0:tabLayout.getTabAt(i).setIcon(R.drawable.user);
                    break;
                case 1:tabLayout.getTabAt(i).setIcon(R.drawable.books);
                    break;
                case 2:tabLayout.getTabAt(i).setIcon(R.drawable.f);
                    break;
                case 3:tabLayout.getTabAt(i).setIcon(R.drawable.dow);
                    break;
                 }
        }
    }

    public  int getSize() {

        return mfragments.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch(position) {
            case 0:tabLayout.setSelectedTabIndicatorColor(tabLayout.getResources().getColor(R.color.personal));
                break;
            case 1:tabLayout.setSelectedTabIndicatorColor(tabLayout.getResources().getColor(R.color.parents));
                break;
            case 2:tabLayout.setSelectedTabIndicatorColor(tabLayout.getResources().getColor(R.color.education));
                break;
            case 3:tabLayout.setSelectedTabIndicatorColor(tabLayout.getResources().getColor(R.color.score));
                break;


        }

    }



    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public Fragment getItem(int position) {
        return mfragments.get(position);
    }
}
