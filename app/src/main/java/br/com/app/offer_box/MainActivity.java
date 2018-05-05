package br.com.app.offer_box;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.app.offer_box.adapter.BiddingCustomAdapter;
import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.webservice.APIClient;
import br.com.app.offer_box.webservice.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    private APIInterface apiService;
    private Call<ArrayList<Bidding>> callListBidding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.listBidding);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        apiService = APIClient.getServiceNode().create(APIInterface.class);
        callListBidding = apiService.getListBidding();

        callListBidding.enqueue(new Callback<ArrayList<Bidding>>() {
            @Override
            public void onResponse(Call<ArrayList<Bidding>> call, Response<ArrayList<Bidding>> response) {

                ArrayList<Bidding> result = response.body();

                BiddingCustomAdapter biddingCustomAdapter;
                biddingCustomAdapter = new BiddingCustomAdapter(MainActivity.this, result);

                recyclerView.setAdapter(biddingCustomAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Bidding>> call, Throwable t) {
                Log.e("Networking", t.toString());
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), BiddingActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
