package com.tsr.tsrproblemreport.testtimeline;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.activity.UI_SHOW_PROBLEM;

import java.util.List;
/**
 * Created by JUNED on 6/16/2016.
 */
public class RecyclerViewAdapter_TIMELINE extends RecyclerView.Adapter<RecyclerViewAdapter_TIMELINE.ViewHolder> {

    Context context;

    List<GetDataAdapter_TIMELINE> getDataAdapter;

    ImageLoader imageLoader1,imageLoader2;

    public RecyclerViewAdapter_TIMELINE(List<GetDataAdapter_TIMELINE> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_timeline, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        GetDataAdapter_TIMELINE getDataAdapter1 =  getDataAdapter.get(position);
        GetDataAdapter_TIMELINE getDataAdapter2 =  getDataAdapter.get(position);
        //  FeddProperties fp = dataSet.get(i);
        imageLoader1 = ServerImageParseAdapter_TIMELINE.getInstance(context).getImageLoader();

        imageLoader1.get(getDataAdapter1.getpicture(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );



        imageLoader2 = ServerImageParseAdapter_TIMELINE2.getInstance(context).getImageLoader();

        imageLoader2.get(getDataAdapter2.getpicture2(),
                ImageLoader.getImageListener(
                        Viewholder.networkImageView2,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );


        Viewholder.networkImageView.setImageUrl(getDataAdapter1.getpicture(), imageLoader1);
        Viewholder.networkImageView2.setImageUrl(getDataAdapter1.getpicture2(), imageLoader2);


        Viewholder.lbl_name.setText(getDataAdapter1.getname());
        Viewholder.lbl_masseger.setText(getDataAdapter1.getmassesger());
        Viewholder.lbl_time.setText(getDataAdapter1.getDate_Time());


        Viewholder.feed = getDataAdapter1;

    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }












    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView lbl_name;
        public TextView lbl_masseger;
        public TextView lbl_time;
       // public TextView status;

        public NetworkImageView networkImageView ;
        public NetworkImageView networkImageView2 ;


        public GetDataAdapter_TIMELINE feed;
        public ViewHolder(View itemView) {

            super(itemView);






            lbl_name = (TextView) itemView.findViewById(R.id.lbl_name) ;
            lbl_masseger = (TextView) itemView.findViewById(R.id.lbl_masseger) ;
            lbl_time = (TextView) itemView.findViewById(R.id.lbl_time) ;
          //  status= (Image) itemView.findViewById(R.id.status) ;
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;
            networkImageView2 = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView2) ;




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent mIntent = new Intent(v.getContext(), UI_SHOW_PROBLEM.class);



                    v.getContext().startActivity(mIntent);








                }
            });





        }
    }
}
