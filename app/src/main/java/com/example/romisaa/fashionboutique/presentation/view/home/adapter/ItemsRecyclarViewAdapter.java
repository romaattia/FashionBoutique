package com.example.romisaa.fashionboutique.presentation.view.home.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.presentation.view.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsRecyclarViewAdapter extends RecyclerView.Adapter<ItemsRecyclarViewAdapter.MyViewHolder> {

    private List<ProductItemModel> items;
    private ItemClickListener listener;

    public ItemsRecyclarViewAdapter(ItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<ProductItemModel> items) {
        this.items = new ArrayList<>();
        for (ProductItemModel model : items) {
            this.items.add(model);
        }
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick();
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.desc_tv)
        TextView descTv;
        @BindView(R.id.price_tv)
        TextView priceTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ProductItemModel model) {
            descTv.setText(model.getDescription());
            priceTv.setText(model.getPrice());
            iv.setImageBitmap(decodeBase64(model.getImage()));
        }

        private Bitmap decodeBase64(String input) {
            byte[] decodedBytes = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        }
    }
}