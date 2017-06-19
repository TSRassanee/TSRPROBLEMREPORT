package com.tsr.tsrproblemreport.testtimeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tsr.tsrproblemreport.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UI_CARDVIEW_TIMELINE extends Fragment {

    List<GetDataAdapter_TIMELINE> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://www.arduinocodes.com/json/getproduc3.php";

    String JSON_NAME = "name";
    String JSON_masseger = "masseger";
    String JSON_time = "time";
    String JSON_picture_profile = "picture_profile";
    String JSON_picture = "picture";
  //  String JSON_status = "status";


    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;
    private SwipeRefreshLayout swipeRefreshLayout;

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
         //       recyclerView.setHasFixedSize(true);

               // recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

              //  recyclerView.setLayoutManager(recyclerViewlayoutManager);


             //   JSON_DATA_WEB_CALL();

                swipeRefreshLayout.setRefreshing(false);
            }
        });





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.cardview_row, container, false);
        // toolbar = (Toolbar) layoutView.findViewById(R.id.toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout)layoutView.findViewById(R.id.swipe_refresh_layout);
        //  swipeRefreshLayout.setOnRefreshListener(getActivity());
        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView)layoutView. findViewById(R.id.recyclerview1);


        return layoutView;

    }


    public void JSON_DATA_WEB_CALL(){

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            GetDataAdapter_TIMELINE GetDataAdapter2 = new GetDataAdapter_TIMELINE();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);





                GetDataAdapter2.setname(json.getString(JSON_NAME));
                GetDataAdapter2.setmassesger(json.getString(JSON_masseger));
                GetDataAdapter2.setDate_Time(json.getString(JSON_time));
                GetDataAdapter2.setpicture(json.getString(JSON_picture_profile));
                GetDataAdapter2.setpicture2(json.getString(JSON_picture));
               // GetDataAdapter2.setstatus(json.getString(JSON_status));








            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter_TIMELINE(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
}