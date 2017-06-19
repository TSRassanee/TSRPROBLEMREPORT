package com.tsr.tsrproblemreport.adapterscollview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART1;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART2;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART3;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART4;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART5;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_DEPART6;


public class CustomFragmentPageAdapterScollview5 extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapterScollview1.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 6;

    public CustomFragmentPageAdapterScollview5(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UI_CARDVIEW_SALE_DEPART1();
            case 1:
                return new UI_CARDVIEW_SALE_DEPART2();
            case 2:

                return new UI_CARDVIEW_SALE_DEPART3();
            case 3:

                return new UI_CARDVIEW_SALE_DEPART4();
            case 4:

                return new UI_CARDVIEW_SALE_DEPART5();
            case 5:

                return new UI_CARDVIEW_SALE_DEPART6();
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
                return "หน.ทีม";
            case 3:
                return "ซุปเปอร์ฯ";
            case 4:
                return "ผจก.สาย";
            case 5:
                return "ผจก.ฝ่าย";
        }
        return null;
    }
}
