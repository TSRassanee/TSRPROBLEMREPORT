package com.tsr.tsrproblemreport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tsr.tsrproblemreport.activity.AboutUsActivity;
import com.tsr.tsrproblemreport.activity.ManageSharedPreferences;
import com.tsr.tsrproblemreport.activity.UI_LOGIN;
import com.tsr.tsrproblemreport.fragment.HomeFragment5;
import com.tsr.tsrproblemreport.fragment.LibraryFragment10;
import com.tsr.tsrproblemreport.fragment.LibraryFragment9;
import com.tsr.tsrproblemreport.fragment.SettingsFragment;
import com.tsr.tsrproblemreport.fragmentIconTextTab.IconTextTabsActivity1;
import com.tsr.tsrproblemreport.fragmentIconTextTab.IconTextTabsActivity11;
import com.tsr.tsrproblemreport.fragmentIconTextTab.IconTextTabsActivity5;
import com.tsr.tsrproblemreport.other.CircleTransform;
import com.tsr.tsrproblemreport.recycleviewuseruser.UI_CARDVIEW_SALE_USER_user;

import static com.tsr.tsrproblemreport.R.id.profile_name;

public class MusicActivity5 extends AppCompatActivity {

    private static final String TAG = MusicActivity5.class.getSimpleName();
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private NavigationView navigationView;

    View header;
    TextView profileName,positionprofile;
    private ImageView imgNavHeaderBg, imgProfile;
    private int count = 0;
    String urlNavHeaderBg = "",urlProfileImg = "";
    String namethai="",picture="",backgroud="",teamleader="",name_teamleader="",username="",position="",status="",Date_issue="",logo="";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new IconTextTabsActivity5();
        fragmentTransaction.replace(R.id.main_container_wrapper, fragment);
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        header= navigationView.inflateHeaderView(R.layout.nav_header_music);
        profileName= (TextView) header.findViewById(profile_name);
        positionprofile = (TextView) header.findViewById(R.id.position);

        imgNavHeaderBg = (ImageView) header.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) header.findViewById(R.id.img_profile);






        Bundle data=getIntent().getExtras();
        if(data!=null) {
            //id = data.getString("id");
            namethai = data.getString("namethai");
            position= data.getString("position");
            picture = data.getString("picture");

            backgroud = data.getString("backgroud");
            teamleader = data.getString("teamleader");
            name_teamleader = data.getString("name_teamleader");
            username = data.getString("username");

            urlProfileImg=picture;
            urlNavHeaderBg=backgroud;

            ManageSharedPreferences.SavePreferences(MusicActivity5.this,getLocalClassName(),"username",username);
            ManageSharedPreferences.SavePreferences(MusicActivity5.this,getLocalClassName(),"namethai",namethai);
            ManageSharedPreferences.SavePreferences(MusicActivity5.this,getLocalClassName(),"position",position);
            ManageSharedPreferences.SavePreferences(MusicActivity5.this,getLocalClassName(),"picture",picture);
            ManageSharedPreferences.SavePreferences(MusicActivity5.this,getLocalClassName(),"backgroud",backgroud);
          //  ManageSharedPreferences.SavePreferences(MusicActivity4.this,getLocalClassName(),"salemanager",salemanager);
           // ManageSharedPreferences.SavePreferences(MusicActivity4.this,getLocalClassName(),"name_salemanager",name_salemanager);

        }













        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    fragment = new IconTextTabsActivity5();
                } else if (id == R.id.nav_user) {
                    fragment = new IconTextTabsActivity11();
                } else if (id == R.id.nav_report1) {
                    fragment = new LibraryFragment9();
                } else if (id == R.id.nav_report2) {
                    fragment = new LibraryFragment10();
                }else if (id == R.id.nav_about_us) {
                    startActivity(new Intent(MusicActivity5.this, AboutUsActivity.class));
                    return true;
                }else if (id == R.id.nav_privacy_policy) {
                    startActivity(new Intent(MusicActivity5.this, UI_CARDVIEW_SALE_USER_user.class));
                    return true;
                }else if (id == R.id.nav_profile) {
                    fragment = new HomeFragment5();
                }
                else if (id == R.id.nav_settings) {
                    fragment = new SettingsFragment();
                }
                else if (id == R.id.nav_logout) {
                    fragment = new IconTextTabsActivity1();
                }

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container_wrapper, fragment);
                transaction.commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                assert drawer != null;
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });















        loadNavHeader();




        Log.e(TAG, position +"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"+ urlProfileImg);


    }



    private void loadNavHeader() {



        profileName.setText(namethai);
        positionprofile.setText(position);
        // String strurl=Utils.UPLOAD_URL+pictureurl;
        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Loading profile image
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // showing dot next to notifications label
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music, menu);
        MenuItem menuItem = menu.findItem(R.id.testAction);
    //    MenuItem menuItem2 = menu.findItem(R.id.action_ses);
       // menuItem2.setIcon(buildCounterDrawable2(count, R.drawable.magnifier));
       menuItem.setIcon(buildCounterDrawable(count, android.R.drawable.ic_popup_reminder));
        return true;
    }


    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.counter_menuitem_layout, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private Drawable buildCounterDrawable2(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.searching_menuitem_layout, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void doIncrease() {
        count++;
        invalidateOptionsMenu();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            String lang = ManageSharedPreferences.LoadPreferences(this, UI_LOGIN.NOTI_ADD, "lang");
            if(lang.equals("th"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("�س��ͧ��÷����͡�ҡ�к�? ")
                        .setCancelable(false)
                        .setPositiveButton("��", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ManageSharedPreferences.SavePreferences(MusicActivity5.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINUSER, "");
                                ManageSharedPreferences.SavePreferences(MusicActivity5.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPASS, "");
                                //   ManageSharedPreferences.SavePreferences(MainActivity.this, LOGIN.NOTI_ADD, LOGIN.LOGINPOSITION, "");
                                UI_LOGIN.logout=true;
                                Intent intent=new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }

                        })
                        .setNegativeButton("���", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ManageSharedPreferences.SavePreferences(MusicActivity5.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINUSER, "");
                                ManageSharedPreferences.SavePreferences(MusicActivity5.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPASS, "");
                                //ManageSharedPreferences.SavePreferences(MainActivity.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPOSITION, "");
                                UI_LOGIN.logout=true;
                                // Intent intent=new Intent();
                                //   setResult(RESULT_OK, intent);
                                // startActivity(intent);
                                //finish();

                                Intent Mintent = new Intent(MusicActivity5.this, UI_LOGIN.class);
                                startActivity(Mintent);
                                finish();

                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }





            // Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            // return true;
        }


        if (id == R.id.testAction) {
            doIncrease();
        }
        //if (id == R.id.action_ses) {
       //     startActivity(new Intent(MusicActivity5.this, PrivacyPolicyActivity.class));
       //     return true;
      //  }
        return super.onOptionsItemSelected(item);
    }

}
