package br.com.app.offer_box;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.app.offer_box.adapter.OfferCustomAdapter;
import br.com.app.offer_box.adapter.TagCustomAdapter;
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

        ed_product = (EditText) findViewById(R.id.ed_product);
        ed_qtd = (EditText) findViewById(R.id.ed_qtd);
        ed_lat = (EditText) findViewById(R.id.ed_lat);
        ed_lng = (EditText) findViewById(R.id.ed_lng);
        btn_env = (Button) findViewById(R.id.btn_env);

        recyclerView = (RecyclerView) findViewById(R.id.listTag);
        mLayoutManager = new LinearLayoutManager(this);
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

                Bidding bidding = new Bidding(product, qtd, Long.parseLong(lat), Long.parseLong(lng), tagCustomAdapter.getListTag());
                envBidding(bidding);
            }
        });
    }


    public void envBidding(Bidding bidding) {
        apiService = APIClient.getServiceNode().create(APIInterface.class);
        callEnvBidding = apiService.envBidding(bidding);

        callEnvBidding.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String result = response.body();
                Log.e("Response", result.toString());

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });

    }
}
