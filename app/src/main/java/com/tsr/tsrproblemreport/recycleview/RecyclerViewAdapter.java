package com.tsr.tsrproblemreport.recycleview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tsr.tsrproblemreport.activity.UI_SHOW_PROBLEM;
import com.tsr.tsrproblemreport.activity.Utils;

import java.util.List;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.tsr.tsrproblemreport.R.drawable.bg_circle2;
import static com.tsr.tsrproblemreport.R.id;
import static com.tsr.tsrproblemreport.R.layout;
import static com.tsr.tsrproblemreport.R.mipmap;

/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> getDataAdapter;

    ImageLoader imageLoader1;
    String s1,s2,s3,s4,s5,s6;
    int converted1,converted2,converted3,converted4,converted5,converted6;
    String ggg;
    String CP;
    public RecyclerViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(layout.show_cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);
      //  FeddProperties fp = dataSet.get(i);
        imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getpicture(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getpicture(), imageLoader1);

        Viewholder.lbl_sale.setText(getDataAdapter1.getnamethai());
        Viewholder.lblposition.setText(getDataAdapter1.getposition());
        Viewholder.lbl_name.setText(getDataAdapter1.getContract_number());
        Viewholder.title.setText(getDataAdapter1.getname_topice());
        Viewholder.lbl_customer.setText(getDataAdapter1.getCustomer());
        Viewholder.lbl_Description.setText(getDataAdapter1.getDescription());
        Viewholder.lbl_date.setText(getDataAdapter1.getDate_Time());
       // Viewholder.lbl_status.setText(getDataAdapter1.getstatus());

        CP=getDataAdapter1.getposition();
        String STATUS=getDataAdapter1.getstatus();
        if(STATUS.equals("0")){
            Viewholder.lbl_status.setText("แก้ไขปัญหาแล้ว");
            Viewholder.lbl_status.setTextColor(0xFF00b300);
        }
        else{
            Viewholder.lbl_status.setText("ยังไม่แก้ไข");
            Viewholder.lbl_status.setTextColor(0xFFf40707);
        }





        String fff=Utils.getSystemDateTextMonth();
    ggg=getDataAdapter1.getDate_Time();










        if(ggg.indexOf(ggg) != -1) {
            String arr2[] = ggg.split("-");
            s4=arr2[0];
            s5=arr2[1];
            s6=arr2[2];
            converted4=Integer.parseInt(s4);
        //    converted5=Integer.parseInt(s5);
            converted6=Integer.parseInt(s6);
        }

        if(fff.indexOf(fff) != -1) {
            String arr[] = fff.split("-");
             s1=arr[0];
             s2=arr[1];
             s3=arr[2];
            converted1=Integer.parseInt(s1);
//             converted2=Integer.parseInt(s2);
             converted3=Integer.parseInt(s3);
        }

        if(s2.equals("January")) {converted2 = 1;}
        else if(s2.equals("February")) {converted2 = 2;}
        else if(s2.equals("March")) {converted2 = 3;}
        else if(s2.equals("April")) {converted2 = 4;}
        else if(s2.equals("May")) {converted2 = 5;}
        else if(s2.equals("June")) {converted2 = 6;}
        else if(s2.equals("July")) {converted2 = 7;}
        else if(s2.equals("August")) {converted2 = 8;}
        else if(s2.equals("September")) {converted2 = 9;}
        else if(s2.equals("October")) {converted2 = 10;}
        else if(s2.equals("November")) {converted2 = 11;}
        else if(s2.equals("December")) {converted2 = 12;}
        else {
           Log.e(TAG,"หมู xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+s2);
        }


//19-18=1
     int data=   converted1-converted4;
        Log.e(TAG,"หมู "+s2+"aaaa"+s5+"aaa"+data+"ss"+ggg);
//if((data<=7)&(converted2==converted5)&(converted3==converted4)){
        if((data<=2)&(s2.equals(s5))&(s3.equals(s6))){
        Viewholder.count.setText("NEW");
        Viewholder.count.setBackgroundResource(bg_circle2);
            }
      else{
        Viewholder.count.setText("");
        Viewholder.count.setBackgroundResource(0);
     }







        Viewholder.feed = getDataAdapter1;

    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }












    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView lbl_sale;
        public TextView lblposition;
        public TextView lbl_name;
        public TextView title;
        public TextView lbl_customer;
        public TextView lbl_Description;
        public TextView lbl_date;
        public TextView lbl_status;
        public TextView count;

        public NetworkImageView networkImageView ;



        public GetDataAdapter feed;
        public ViewHolder(View itemView) {

            super(itemView);






            lbl_sale = (TextView) itemView.findViewById(id.lbl_sale) ;
            lblposition = (TextView) itemView.findViewById(id.lblposition) ;
            lbl_name = (TextView) itemView.findViewById(id.lbl_name) ;
            title = (TextView) itemView.findViewById(id.title) ;
            lbl_customer = (TextView) itemView.findViewById(id.lbl_customer) ;
            lbl_Description = (TextView) itemView.findViewById(id.lbl_masseger) ;
            lbl_date = (TextView) itemView.findViewById(id.lbl_time) ;
            count = (TextView) itemView.findViewById(id.count) ;



            lbl_status = (TextView) itemView.findViewById(id.lbl_status) ;

            networkImageView = (NetworkImageView) itemView.findViewById(id.VollyNetworkImageView1) ;





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                   //String ckeckposition= getDataAdapter1.getposition();

                        Intent mIntent = new Intent(v.getContext(), UI_SHOW_PROBLEM.class);
                        Bundle bun=new Bundle();
                        bun.putString("namethai", feed.getnamethai());
                        bun.putString("position", feed.getposition());
                        bun.putString("picture", feed.getpicture());
                        bun.putString("Contract_number", feed.getContract_number());
                        bun.putString("name_topic", feed.getname_topice());
                        bun.putString("Customer", feed.getCustomer());
                        bun.putString("Description", feed.getDescription());
                        bun.putString("Date_Time", feed.getDate_Time());
                        bun.putString("status", feed.getstatus());

                        //title.setText()

                        mIntent.putExtras(bun);

                        v.getContext().startActivity(mIntent);












                }
            });





        }
    }
}
