package br.com.app.offer_box;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.Interested;
import br.com.app.offer_box.model.OfferProduct;
import br.com.app.offer_box.webservice.APIClient;
import br.com.app.offer_box.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoOfferActivity extends AppCompatActivity {

    private APIInterface apiService;
    private Call<OfferProduct> callInfoOffer;
    private Call<String> callUpdateOffer;

    TextView tv_price_unit;
    TextView tv_name_company;
    TextView tv_product;
    TextView tv_distance;
    TextView qtd_offers_company;
    TextView type_company;
    Button btn_interested;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_offer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("id");

        apiService = APIClient.getServiceNode().create(APIInterface.class);
        callInfoOffer = apiService.getInfoOffer(id);

        Log.e("request", callInfoOffer.request().url().toString());

        tv_price_unit = findViewById(R.id.tv_price_unit);
        tv_name_company = findViewById(R.id.tv_name_company);;
        tv_product = findViewById(R.id.tv_product);;
        tv_distance = findViewById(R.id.tv_distance);;
        qtd_offers_company = findViewById(R.id.qtd_offers_company); ;
        type_company = findViewById(R.id.type_company);
        btn_interested = findViewById(R.id.btn_interested);

        btn_interested.setText("I'm interested");

        btn_interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Interested interested = new Interested();
                interested.setId(id);
                interested.setInterested("true");

                callUpdateOffer = apiService.updateOffer(interested);

                callUpdateOffer.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String result = response.body();
                        Log.e("Response", result.toString());

                        Toast.makeText(getApplicationContext(), "Interested inserted with success", Toast.LENGTH_LONG).show();

                        onBackPressed();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("Networking", t.toString());
                    }
                });

            }
        });

        callInfoOffer.enqueue(new Callback<OfferProduct>() {
            @Override
            public void onResponse(Call<OfferProduct> call, Response<OfferProduct> response) {

                OfferProduct result = response.body();

                tv_price_unit.setText("Price (Unit): " + String.valueOf(result.getPrice_unit()));
                tv_name_company.setText("Name Company: " + String.valueOf(result.getName_company()));
                tv_product.setText(String.valueOf("Name Product/Service: " + result.getProduct()));
                tv_distance.setText(String.valueOf("Distance: " + result.getDistance()));
                qtd_offers_company.setText(String.valueOf("Amount Offers Company: " + result.getQtd_offers_company()));
                type_company.setText(String.valueOf("Type Company: " + result.getType_company()));

                Log.e("Response", result.toString());
            }

            @Override
            public void onFailure(Call<OfferProduct> call, Throwable t) {
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
