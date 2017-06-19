package com.tsr.tsrproblemreport.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsr.tsrproblemreport.MusicActivity2;
import com.tsr.tsrproblemreport.R;

import java.io.InputStream;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class UI_SHOW_PROBLEM_SALELEADER extends AppCompatActivity implements OnClickListener {
    String filePath = null;
    public static final int PICK_IMAGE=101;
    TextView txtname,txtprice,txtname_sale,txt_position,txt_cnumber,txt_customer,txt_date,txt_status;
    Button button5,button6,btnbuy=null;
    ImageView imglogo=null;

    String name="",price="",Sales_person="",position="",Contract_number="",Customer="",Description="",status="",Date_Time="",blogo="";

    String id="";


    private ProgressDialog pdialog;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_problem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnbuy=(Button)findViewById(R.id.btnbuy);
        button5=(Button)findViewById(R.id.button5);
        //  button6=(Button)findViewById(R.id.button6);

        txtname = (TextView)findViewById(R.id.txtname);
        txtprice = (TextView)findViewById(R.id.txtprice);

        txtname_sale = (TextView)findViewById(R.id.txtname_sale);
        txt_position = (TextView)findViewById(R.id.txt_position);
        txt_cnumber = (TextView)findViewById(R.id.txt_cnumber);
        txt_customer = (TextView)findViewById(R.id.txt_customer);
        txt_date = (TextView)findViewById(R.id.txt_date);
        txt_status = (TextView)findViewById(R.id.txt_status);





        imglogo = (ImageView)findViewById(R.id.imgproduct);
        btnbuy.setOnClickListener(this);
        button5.setOnClickListener(this);
        //   button6.setOnClickListener(this);


        // imglogo.setOnClickListener(this);
        pdialog = new ProgressDialog(this);
        pdialog.setMessage("Please wait...");
        pdialog.setCancelable(false);
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setMax(100);
        Bundle data=getIntent().getExtras();
        if(data!=null)
        {
            id=data.getString("id");
            Sales_person=data.getString("namethai");
            position=data.getString("position");
            blogo=data.getString("picture");
            Contract_number=data.getString("Contract_number");
            name=data.getString("name_topic");
            Customer=data.getString("Customer");
            Description=data.getString("Description");
            Date_Time=data.getString("Date_Time");
            status=data.getString("status");













            txtname.setText(name);
            txtprice.setText(Description);

            txtname_sale.setText(Sales_person);
            txt_position.setText(position);
            txt_cnumber.setText(Contract_number);
            txt_customer.setText(Customer);
            txt_date.setText(Date_Time);
            txt_status.setText(status);

            new DownloadImageFromInternet((ImageView) findViewById(R.id.imgproduct))
                    .execute(blogo);


        }



    }




    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            // Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v==btnbuy)
        {
            name=txtname.getText().toString();
            price=txtprice.getText().toString();


        }

        else if(v==button5)
        {





            String DSA=ManageSharedPreferences.LoadPreferences(this,MusicActivity2.class.getSimpleName(),"picture");
            Log.e(TAG, position +"asaaaaaaaaaaaaa"+ DSA);
            if(blogo.equals(DSA)){
                Intent mIntent = new Intent( this,UI_PROBLEM_ALARM.class);
                Bundle bun=new Bundle();

                bun.putString("Sales_person", Sales_person);
                bun.putString("blogo", blogo);
                bun.putString("Description", Description);

                mIntent.putExtras(bun);


                startActivity(mIntent);

            }
            else{
                Utils.showDialog(this, "ไม่สามารถแจ้งการแก้ไขปัญหาได้ เนื่องจากไม่ไช่ปัญหาของคุณ");
            }









        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
