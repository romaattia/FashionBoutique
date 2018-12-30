package com.example.romisaa.fashionboutique.presentation.view.feedback;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private List<FeedbackModel> items;

    public void setData(List<FeedbackModel> items) {
        this.items = new ArrayList<>();
        for (FeedbackModel model : items) {
            this.items.add(model);
        }
        notifyDataSetChanged();
    }

    @Override
    public FeedbackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feedback_card, parent, false);

        return new FeedbackAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(items.get(i));
    }


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.feedback_tv)
        TextView feedbackTv;
        @BindView(R.id.num_tv)
        TextView numTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FeedbackModel model) {
            feedbackTv.setText(model.getFeedback());
            numTv.setText(model.getNumber());
        }
    }
}
