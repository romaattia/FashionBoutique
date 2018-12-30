package com.example.romisaa.fashionboutique.presentation.view.feedback;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.List;

import butterknife.BindView;

public class FeedbackFragment extends BaseFragment<FeedbackViewModel> {

    @BindView(R.id.rv)
    RecyclerView rv;

    private FeedbackAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(FeedbackViewModel.class);
    }

    @Override
    protected int getViewType() {
        return BusinessConstants.FRAME;
    }

    @Override
    protected void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(layoutManager);
        adapter = new FeedbackAdapter();
        rv.setAdapter(adapter);
    }

    @Override
    protected void callAPIService() {
        viewModel.getFeedbacks();
        viewModel.itemsliveData.observe(this, new Observer<List<FeedbackModel>>() {
            @Override
            public void onChanged(@Nullable List<FeedbackModel> feedbackViewModels) {
                adapter.setData(feedbackViewModels);
            }
        });
    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }

}
