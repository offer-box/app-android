package br.com.app.offer_box;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.OfferProduct;
import br.com.app.offer_box.webservice.APIClient;
import br.com.app.offer_box.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoOfferActivity extends AppCompatActivity {

    private String id;

    private APIInterface apiService;
    private Call<OfferProduct> callInfoOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_offer);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("id");

        apiService = APIClient.getService().create(APIInterface.class);
        callInfoOffer = apiService.getInfoOffer(id);

        callInfoOffer.enqueue(new Callback<OfferProduct>() {
            @Override
            public void onResponse(Call<OfferProduct> call, Response<OfferProduct> response) {

                OfferProduct result = response.body();
                Log.e("Response", result.toString());

            }

            @Override
            public void onFailure(Call<OfferProduct> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });
    }
}
