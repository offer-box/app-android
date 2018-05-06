package br.com.app.offer_box.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.app.offer_box.ListOfferActivity;
import br.com.app.offer_box.R;
import br.com.app.offer_box.model.Bidding;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class BiddingCustomAdapter extends RecyclerView.Adapter<BiddingCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<Bidding> biddingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_product;
        TextView tv_qtd;

        ImageView iv_icon;
        ImageView iv_icon_score;
        LinearLayout ll_line;

        public MyViewHolder(View view) {
            super(view);
            tv_product = (TextView) view.findViewById(R.id.tv_product);
            tv_qtd = (TextView) view.findViewById(R.id.tv_qtd);
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            ll_line = (LinearLayout) view.findViewById(R.id.ll_line);
        }
    }

    public BiddingCustomAdapter(Context mContext, List<Bidding> biddingList) {
        this.mContext = mContext;
        this.biddingList = biddingList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_bidding, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Bidding bidding = biddingList.get(position);
        holder.tv_product.setText("Product/Service: " + bidding.getProduct());
        holder.tv_qtd.setText("Amount: " + bidding.getQtd());
//        holder.iv_icon.setImageResource(R.drawable.computer);



        holder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ListOfferActivity.class);
                intent.putExtra("product", "" + bidding.getProduct());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return biddingList.size();
    }
}
