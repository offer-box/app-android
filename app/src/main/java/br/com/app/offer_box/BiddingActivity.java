package br.com.app.offer_box;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.app.offer_box.adapter.OfferCustomAdapter;
import br.com.app.offer_box.adapter.TagCustomAdapter;
import br.com.app.offer_box.helper.GPSTracker;
import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.Tag;
import br.com.app.offer_box.webservice.APIClient;
import br.com.app.offer_box.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiddingActivity extends AppCompatActivity {

    EditText ed_product;
    EditText ed_qtd;
    EditText ed_lat;
    EditText ed_lng;
    Button btn_env;

    String product, qtd, lat, lng;

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private APIInterface apiService;
    private Call<String> callEnvBidding;
    private Call<ArrayList<String>> callListTags;

    private TagCustomAdapter tagCustomAdapter;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed_product = (EditText) findViewById(R.id.ed_product);
        ed_qtd = (EditText) findViewById(R.id.ed_qtd);
        ed_lat = (EditText) findViewById(R.id.ed_lat);
        ed_lng = (EditText) findViewById(R.id.ed_lng);
        btn_env = (Button) findViewById(R.id.btn_env);

        recyclerView = (RecyclerView) findViewById(R.id.listTag);
//        mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);

        tagCustomAdapter = null;

        apiService = APIClient.getServiceJava().create(APIInterface.class);

        callListTags = apiService.getListTag();
        callListTags.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {

                ArrayList<String> result = response.body();

                List<Tag> tags = new ArrayList<Tag>();
                for(String description : result) {
                    tags.add(new Tag(description, false));
                }

                tagCustomAdapter = new TagCustomAdapter(BiddingActivity.this, tags);
                recyclerView.setAdapter(tagCustomAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });

        btn_env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product = ed_product.getText().toString();
                qtd = ed_qtd.getText().toString();
                lat = ed_lat.getText().toString();
                lng = ed_lng.getText().toString();
                Bidding bidding = new Bidding(product, qtd, lat, lng, tagCustomAdapter.getListTag());
                envBidding(bidding);
            }
        });

        getGeoLocation();
    }

    GPSTracker gps;

    public void getGeoLocation() {
        gps = new GPSTracker(BiddingActivity.this);
        // check if GPS enabled
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            
            ed_lat.setText(String.valueOf(latitude));
            ed_lng.setText(String.valueOf(longitude));

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }


    public void envBidding(Bidding bidding) {
        apiService = APIClient.getServiceNode().create(APIInterface.class);
        callEnvBidding = apiService.envBidding(bidding);

        callEnvBidding.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String result = response.body();
                Toast.makeText(getApplicationContext(), "Insert Success", Toast.LENGTH_LONG).show();
                onBackPressed();

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
