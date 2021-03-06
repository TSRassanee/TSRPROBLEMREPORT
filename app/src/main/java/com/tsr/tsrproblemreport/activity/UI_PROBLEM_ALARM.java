package com.tsr.tsrproblemreport.activity;


import android.app.ProgressDialog;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsr.tsrproblemreport.R;

import java.io.InputStream;

public class UI_PROBLEM_ALARM extends AppCompatActivity implements OnClickListener {
    String filePath = null;
    public static final int PICK_IMAGE=101;
    TextView txtname,txtprice,txtname_sale,txt_position,txt_cnumber,txt_customer,txt_date,txt_status;
    Button button5,button6,btnbuy=null;
    ImageView imglogo=null;

    String name="",price="",Sales_person="",position="",Contract_number="",Customer="",Description="",status="",Date_Time="",blogo="";

    String id="";


    private ProgressDialog pdialog;
CheckBox checkBox,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnbuy=(Button)findViewById(R.id.btnbuy);
        button5=(Button)findViewById(R.id.button5);
        //  button6=(Button)findViewById(R.id.button6);


        txtprice = (TextView)findViewById(R.id.txtprice);
        txtname_sale = (TextView)findViewById(R.id.txtname_sale);

        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox4=(CheckBox)findViewById(R.id.checkBox4);
        checkBox5=(CheckBox)findViewById(R.id.checkBox5);
        checkBox6=(CheckBox)findViewById(R.id.checkBox6);
        checkBox7=(CheckBox)findViewById(R.id.checkBox7);
        checkBox8=(CheckBox)findViewById(R.id.checkBox8);





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
            Sales_person=data.getString("Sales_person");

            blogo=data.getString("blogo");

            Description=data.getString("Description");















         //   txtprice.setText(Description);
            txtprice.setText("");
            txtname_sale.setText(Sales_person);

String QQ=Description;



            if(QQ.indexOf(QQ) != -1) {
                String arr[] = QQ.split(",");
                checkBox.setText(arr[0]);
                checkBox2.setText(arr[1]);
            }




            checkBox3.setText("");
          //  checkBox3.setButtonDrawable(0);
            checkBox4.setText("");
          //  checkBox4.setButtonDrawable(0);
            checkBox5.setText("");
         //   checkBox5.setButtonDrawable(0);
            checkBox6.setText("");
       //     checkBox6.setButtonDrawable(0);
            checkBox7.setText("");
        //    checkBox7.setButtonDrawable(0);
            checkBox8.setText("");
        //    checkBox8.setButtonDrawable(0);




            new UI_PROBLEM_ALARM.DownloadImageFromInternet((ImageView) findViewById(R.id.imgproduct))
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
                Log.e("Error MessDDDage", e.getMessage());
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
