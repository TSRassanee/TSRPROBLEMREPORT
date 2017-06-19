package com.tsr.tsrproblemreport.recycleviewuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.recycleviewuseruser.UI_CARDVIEW_SALE_USER_user;

import java.util.List;
/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter_USER extends RecyclerView.Adapter<RecyclerViewAdapter_USER.ViewHolder> {

    Context context;

    List<GetDataAdapter_USER> getDataAdapter;

    ImageLoader imageLoader1;

    public RecyclerViewAdapter_USER(List<GetDataAdapter_USER> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cardview_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetDataAdapter_USER getDataAdapter1 =  getDataAdapter.get(position);
        //  FeddProperties fp = dataSet.get(i);
        imageLoader1 = ServerImageParseAdapter_USER.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getpicture(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getpicture(), imageLoader1);

        Viewholder.lbl_sale.setText(getDataAdapter1.getnamethai());
        Viewholder.lblposition.setText(getDataAdapter1.getposition());

        Viewholder.lbl_Description.setText(getDataAdapter1.getDescription());
        Viewholder.lbl_date.setText(getDataAdapter1.getTime());
        Viewholder.count.setText(getDataAdapter1.getcount_rec());


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



        public GetDataAdapter_USER feed;
        public ViewHolder(View itemView) {

            super(itemView);






            lbl_sale = (TextView) itemView.findViewById(R.id.lbl_sale) ;
            lblposition = (TextView) itemView.findViewById(R.id.lblposition) ;
           // lbl_name = (TextView) itemView.findViewById(R.id.lbl_name) ;
           // title = (TextView) itemView.findViewById(R.id.title) ;
           // lbl_customer = (TextView) itemView.findViewById(R.id.lbl_customer) ;
            lbl_Description = (TextView) itemView.findViewById(R.id.lbl_masseger) ;
            lbl_date = (TextView) itemView.findViewById(R.id.lbl_time) ;
         //   lbl_status = (TextView) itemView.findViewById(R.id.lbl_status) ;
            count = (TextView) itemView.findViewById(R.id.count) ;
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                //    Intent mIntent = new Intent(v.getContext(), UI_CARDVIEW_SALE_USER_user.class);
                   // Intent mIntent = new Intent( this, UI_CARDVIEW_SALE_USER_user.class);



                    Intent mIntent = new Intent( context,UI_CARDVIEW_SALE_USER_user.class);
                    Bundle bun=new Bundle();


                    bun.putString("namethai", feed.getnamethai());
                    bun.putString("position", feed.getposition());
                    bun.putString("picture", feed.getpicture());
                    mIntent.putExtras(bun);


                    v.getContext().startActivity(mIntent);








                }
            });





        }
    }
}
