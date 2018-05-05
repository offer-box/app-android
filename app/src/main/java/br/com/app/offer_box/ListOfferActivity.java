package br.com.app.offer_box;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.app.offer_box.adapter.BiddingCustomAdapter;
import br.com.app.offer_box.adapter.OfferCustomAdapter;
import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.OfferProduct;
import br.com.app.offer_box.webservice.APIClient;
import br.com.app.offer_box.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfferActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private String id;

    private APIInterface apiService;
    private Call<ArrayList<OfferProduct>> callListOffer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offer);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("id");

        recyclerView = (RecyclerView) findViewById(R.id.listOffer);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        apiService = APIClient.getServiceNode().create(APIInterface.class);
        callListOffer = apiService.getListOffer(id);

        callListOffer.enqueue(new Callback<ArrayList<OfferProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<OfferProduct>> call, Response<ArrayList<OfferProduct>> response) {

                ArrayList<OfferProduct> result = response.body();

                OfferCustomAdapter offerCustomAdapter;
                offerCustomAdapter = new OfferCustomAdapter(ListOfferActivity.this, result);

                recyclerView.setAdapter(offerCustomAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<OfferProduct>> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });




    }
}
