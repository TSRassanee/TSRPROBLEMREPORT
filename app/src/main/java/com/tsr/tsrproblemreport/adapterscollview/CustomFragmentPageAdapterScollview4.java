package com.tsr.tsrproblemreport.adapterscollview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_LINE1;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_LINE2;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_LINE3;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_LINE4;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_LINE5;


public class CustomFragmentPageAdapterScollview4 extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapterScollview1.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 5;

    public CustomFragmentPageAdapterScollview4(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UI_CARDVIEW_SALE_LINE1();
            case 1:
                return new UI_CARDVIEW_SALE_LINE2();
            case 2:

                return new UI_CARDVIEW_SALE_LINE3();
            case 3:

                return new UI_CARDVIEW_SALE_LINE4();
            case 4:

                return new UI_CARDVIEW_SALE_LINE5();
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
                return "ทั้งหมด";
            case 1:
                return "พน.ขาย";
            case 2:
                return "หน.ขาย";
            case 3:
                return "ซุปเปอร์ฯ";
            case 4:
                return "ผจก.สาย";
        }
        return null;
    }
}
