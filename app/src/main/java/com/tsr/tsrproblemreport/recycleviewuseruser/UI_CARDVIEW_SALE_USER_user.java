package com.tsr.tsrproblemreport.recycleviewuseruser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.recycleview.GetDataAdapter;
import com.tsr.tsrproblemreport.recycleview.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UI_CARDVIEW_SALE_USER_user extends AppCompatActivity {

    List<GetDataAdapter> GetDataAdapter1;

    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    String GET_JSON_DATA_HTTP_URL = "http://app.thiensurat.co.th/assanee/User_user_problem_all.php";
    String picture2;
   // String GET_JSON_DATA_HTTP_URL = "http://app.thiensurat.co.th/assanee/prolem_user_all_sale.php";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_row);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GetDataAdapter1 = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        JSON_DATA_WEB_CALL();


        Bundle data=getIntent().getExtras();
        if(data!=null) {


           // picture2 = data.getString("picture");
       //   picture2 ="http://app.thiensurat.co.th/assanee/upload/image5.png";
          //  Log.e(TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + picture2);
        }

    }

    public void JSON_DATA_WEB_CALL(){
        Bundle data=getIntent().getExtras();
        if(data!=null) {


           picture2 = data.getString("picture");
           // picture2 ="http://app.thiensurat.co.th/assanee/upload/image5.png";
            //  Log.e(TAG, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + picture2);
        }

String D=picture2;
       // jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+"?picture="+"http://app.thiensurat.co.th/assanee/upload/image8.png",
                jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+"?picture="+D,
                //  jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL,
             //   jsonArrayRequest = new JsonArrayRequest(GET_JSON_DATA_HTTP_URL+"?name="+ ManageSharedPreferences.LoadPreferences(this,MusicActivity.class.getSimpleName(), "username"),
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

        requestQueue = Volley.newRequestQueue(this);

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

        recyclerViewadapter = new RecyclerViewAdapter(GetDataAdapter1, this);

        recyclerView.setAdapter(recyclerViewadapter);
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