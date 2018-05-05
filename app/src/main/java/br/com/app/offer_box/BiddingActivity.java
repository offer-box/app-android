package br.com.app.offer_box;

import android.app.ProgressDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.app.offer_box.model.Bidding;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        ed_product = (EditText) findViewById(R.id.ed_product);
        ed_qtd = (EditText) findViewById(R.id.ed_qtd);
        ed_lat = (EditText) findViewById(R.id.ed_lat);
        ed_lng = (EditText) findViewById(R.id.ed_lng);
        btn_env = (Button) findViewById(R.id.btn_env);



        btn_env.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                product = ed_product.getText().toString();
                qtd = ed_qtd.getText().toString();
                lat = ed_lat.getText().toString();
                lng = ed_lng.getText().toString();

                Bidding bidding = new Bidding(product, qtd, Long.parseLong(lat), Long.parseLong(lng));
                envBidding(bidding);

            }
        });
    }

    private APIInterface apiService;
    private Call<String> callEnvBidding;

    public void envBidding(Bidding bidding) {

        apiService = APIClient.getService().create(APIInterface.class);
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
