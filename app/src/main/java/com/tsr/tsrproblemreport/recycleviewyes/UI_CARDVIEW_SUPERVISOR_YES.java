package com.tsr.tsrproblemreport.recycleviewyes;

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
import com.tsr.tsrproblemreport.MusicActivity3;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.activity.ManageSharedPreferences;
import com.tsr.tsrproblemreport.recycleview.GetDataAdapter;
import com.tsr.tsrproblemreport.recycleview.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UI_CARDVIEW_SUPERVISOR_YES extends Fragment {

    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://app.thiensurat.co.th/assanee/supervisor_yes.php";

    String JSON_NAMETHAI = "namethai";
    String JSON_POSITION = "position";
    String JSON_PICTURE = "picture";
    String JSON_CONTACT_NUMBER = "Contract_number";
    String JSON_NAMETOPIC = "name_topic";
    String JSON_CUSTOMER = "Customer";
    String JSON_DESCRIPTION = "Description";
    String JSON_DATE_TIME = "Date_Time";
    String JSON_STATUS = "status";

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
                recyclerView.setHasFixedSize(true);

                recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(recyclerViewlayoutManager);


                JSON_DATA_WEB_CALL();

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

        jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+"?name="+ ManageSharedPreferences.LoadPreferences(getActivity(),MusicActivity3.class.getSimpleName(), "username"),

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

            GetDataAdapter GetDataAdapter2 = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);





                GetDataAdapter2.setnamethai(json.getString(JSON_NAMETHAI));
                GetDataAdapter2.setposition(json.getString(JSON_POSITION));
                GetDataAdapter2.setpicture(json.getString(JSON_PICTURE));
                GetDataAdapter2.setContract_number(json.getString(JSON_CONTACT_NUMBER));
                GetDataAdapter2.setname_topic(json.getString(JSON_NAMETOPIC));
                GetDataAdapter2.setCustomer(json.getString(JSON_CUSTOMER));
                GetDataAdapter2.setDescription(json.getString(JSON_DESCRIPTION));
                GetDataAdapter2.setDate_Time(json.getString(JSON_DATE_TIME));
                GetDataAdapter2.setstatus(json.getString(JSON_STATUS));








            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetDataAdapter1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
}