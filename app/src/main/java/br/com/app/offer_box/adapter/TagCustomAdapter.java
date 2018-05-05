package br.com.app.offer_box.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.app.offer_box.R;
import br.com.app.offer_box.model.Tag;

/**
 * Created by matheuscatossi on 05/05/18.
 */

public class TagCustomAdapter extends RecyclerView.Adapter<TagCustomAdapter.MyViewHolder> {

    private Context mContext;
    private List<Tag> biddingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView tv_description;
        CheckBox cb_checked;
        LinearLayout ll_line;

        public MyViewHolder(View view) {
            super(view);
//            tv_description =  view.findViewById(R.id.tv_description);
            cb_checked =  view.findViewById(R.id.cb_checked);
            ll_line = (LinearLayout) view.findViewById(R.id.ll_line);
        }
    }

    public TagCustomAdapter(Context mContext, List<Tag> tagList) {
        this.mContext = mContext;
        this.biddingList = tagList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_tag, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Tag tag = biddingList.get(position);
//        holder.tv_description.setText("" + tag.getDescription());
        holder.cb_checked.setText("" + tag.getDescription());

        holder.ll_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.cb_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                biddingList.get(position).setChecked(isChecked);
            }
        });

    }

    public List<Tag> getListTag() {
        return biddingList;
    }

    @Override
    public int getItemCount() {
        return biddingList.size();
    }
}
