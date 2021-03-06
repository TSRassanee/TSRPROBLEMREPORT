package com.tsr.tsrproblemreport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tsr.tsrproblemreport.activity.ManageSharedPreferences;
import com.tsr.tsrproblemreport.activity.UI_LOGIN;
import com.tsr.tsrproblemreport.fragment.HomeFragment2;
import com.tsr.tsrproblemreport.fragment.LibraryFragment3;
import com.tsr.tsrproblemreport.fragment.LibraryFragment4;
import com.tsr.tsrproblemreport.fragmentIconTextTab.IconTextTabsActivity1;
import com.tsr.tsrproblemreport.fragmentIconTextTab.IconTextTabsActivity2;
import com.tsr.tsrproblemreport.other.CircleTransform;
import com.tsr.tsrproblemreport.recycleviewuser.UI_CARDVIEW_SALELEADER_USER;

import static com.tsr.tsrproblemreport.R.id.profile_name;

public class MusicActivity2 extends AppCompatActivity {

    private static final String TAG = MusicActivity.class.getSimpleName();
    private Toolbar toolbar;
    private FragmentManager fragmentManager;
    private Fragment fragment = null;
    private NavigationView navigationView;

    View header;
    TextView profileName,positionprofile;
    private ImageView imgNavHeaderBg, imgProfile;

    String urlNavHeaderBg = "",urlProfileImg = "";
    String namethai="",picture="",backgroud="",supervisor="",name_supervisor="",username="",position="",status="",Date_issue="",logo="";
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
        fragment = new IconTextTabsActivity2();
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
            supervisor = data.getString("supervisor");
            name_supervisor = data.getString("name_supervisor");
            username = data.getString("username");

            urlProfileImg=picture;
            urlNavHeaderBg=backgroud;
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"username",username);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"namethai",namethai);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"position",position);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"picture",picture);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"backgroud",backgroud);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"supervisor",supervisor);
            ManageSharedPreferences.SavePreferences(MusicActivity2.this,getLocalClassName(),"name_supervisor",name_supervisor);

        }













        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    fragment = new IconTextTabsActivity2();
                } else if (id == R.id.nav_user) {
                    fragment = new UI_CARDVIEW_SALELEADER_USER();
                } else if (id == R.id.nav_report1) {
                    fragment = new LibraryFragment3();
                } else if (id == R.id.nav_report2) {
                    fragment = new LibraryFragment4();
                }else if (id == R.id.nav_about_us) {
                    fragment = new IconTextTabsActivity1();
                }else if (id == R.id.nav_privacy_policy) {
                    fragment = new IconTextTabsActivity1();
                }else if (id == R.id.nav_profile) {
                    fragment = new HomeFragment2();
                }
                else if (id == R.id.nav_settings) {
                    fragment = new IconTextTabsActivity1();
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
        return true;
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
                                ManageSharedPreferences.SavePreferences(MusicActivity2.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINUSER, "");
                                ManageSharedPreferences.SavePreferences(MusicActivity2.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPASS, "");
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

                                ManageSharedPreferences.SavePreferences(MusicActivity2.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINUSER, "");
                                ManageSharedPreferences.SavePreferences(MusicActivity2.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPASS, "");
                                //ManageSharedPreferences.SavePreferences(MainActivity.this, UI_LOGIN.NOTI_ADD, UI_LOGIN.LOGINPOSITION, "");
                                UI_LOGIN.logout=true;
                                // Intent intent=new Intent();
                                //   setResult(RESULT_OK, intent);
                                // startActivity(intent);
                                //finish();

                                Intent Mintent = new Intent(MusicActivity2.this, UI_LOGIN.class);
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

        return super.onOptionsItemSelected(item);
    }

}
