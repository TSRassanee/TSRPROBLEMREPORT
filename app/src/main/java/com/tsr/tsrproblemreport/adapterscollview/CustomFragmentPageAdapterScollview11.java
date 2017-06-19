package com.tsr.tsrproblemreport.adapterscollview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tsr.tsrproblemreport.recycleviewuser.UI_CARDVIEW_DEPART_USER;


public class CustomFragmentPageAdapterScollview11 extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapterScollview1.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 1;

    public CustomFragmentPageAdapterScollview11(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UI_CARDVIEW_DEPART_USER();

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
                return "แยก user";


        }
        return null;
    }
}
