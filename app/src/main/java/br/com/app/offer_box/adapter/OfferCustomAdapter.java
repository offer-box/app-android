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

import br.com.app.offer_box.InfoOfferActivity;
import br.com.app.offer_box.ListOfferActivity;
import br.com.app.offer_box.R;
import br.com.app.offer_box.model.Bidding;
import br.com.app.offer_box.model.OfferProduct;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class OfferCustomAdapter extends RecyclerView.Adapter<OfferCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<OfferProduct> offerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_price_unit;
        TextView tv_name_company;
//        TextView tv_name_product;
        TextView tv_distance;
        TextView tv_qtd_offers_company;
        TextView tv_type_company;
        ImageView iv_icon;
        LinearLayout ll_line;

        public MyViewHolder(View view) {
            super(view);

            tv_price_unit = (TextView) view.findViewById(R.id.tv_price_unit);
            tv_name_company = (TextView) view.findViewById(R.id.tv_name_company);
//            tv_name_product = (TextView) view.findViewById(R.id.tv_name_product);
            tv_distance = (TextView) view.findViewById(R.id.tv_distance);
            tv_qtd_offers_company = (TextView) view.findViewById(R.id.tv_qtd_offers_company);
            tv_type_company = (TextView) view.findViewById(R.id.tv_type_company);

            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            ll_line = (LinearLayout) view.findViewById(R.id.ll_line);
        }
    }

    public OfferCustomAdapter(Context mContext, List<OfferProduct> offerList) {
        this.mContext = mContext;
        this.offerList = offerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_offer_product, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final OfferProduct offerProduct = offerList.get(position);
        holder.tv_price_unit.setText("Price (Unit): " + String.valueOf(offerProduct.getPrice_unit()));
        holder.tv_name_company.setText("Name Company: " + String.valueOf(offerProduct.getName_company()));
//        holder.tv_name_product.setText("Name Product/Service: " + String.valueOf(offerProduct.getProduct()));
        holder.tv_distance.setText("" + offerProduct.getDistance());
        holder.tv_qtd_offers_company.setText("Amount Offers Company: " + String.valueOf(offerProduct.getQtd_offers_company()));
        holder.tv_type_company.setText("Type Company: " + offerProduct.getType_company());

        holder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InfoOfferActivity.class);
                intent.putExtra("id", "" + offerProduct.getId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }
}
