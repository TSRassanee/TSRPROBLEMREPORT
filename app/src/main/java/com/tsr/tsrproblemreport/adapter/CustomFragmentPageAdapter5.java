package com.tsr.tsrproblemreport.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tsr.tsrproblemreport.recycleviewyes.UI_CARDVIEW_SUPERVISOR_YES;


public class CustomFragmentPageAdapter5 extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 1;

    public CustomFragmentPageAdapter5(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                 return new UI_CARDVIEW_SUPERVISOR_YES();

            /*case 1:
                return new PlaylistFragment();
            case 2:
                return new AlbumFragment();
            case 3:
                return new ArtistFragment();*/
        }
        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "ปัญหาที่แก้ไขแล้ว";

        }
        return null;
    }
}
