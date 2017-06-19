package com.tsr.tsrproblemreport.adapterscollview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_SUPERVISOR1;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_SUPERVISOR2;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_SUPERVISOR3;
import com.tsr.tsrproblemreport.recycleview.UI_CARDVIEW_SALE_SUPERVISOR4;


public class CustomFragmentPageAdapterScollview3 extends FragmentPagerAdapter {

    private static final String TAG = CustomFragmentPageAdapterScollview1.class.getSimpleName();

    private static final int FRAGMENT_COUNT = 4;

    public CustomFragmentPageAdapterScollview3(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new UI_CARDVIEW_SALE_SUPERVISOR1();
            case 1:
                return new UI_CARDVIEW_SALE_SUPERVISOR2();
            case 2:

                return new UI_CARDVIEW_SALE_SUPERVISOR3();
            case 3:

                return new UI_CARDVIEW_SALE_SUPERVISOR4();
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

        }
        return null;
    }
}
