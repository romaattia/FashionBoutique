package com.example.romisaa.fashionboutique.presentation.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.navigation.BaseNavigationManager;
import com.example.romisaa.fashionboutique.data.model.ErrorModel;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<viewModel extends BaseViewModel> extends Fragment {

    protected viewModel viewModel;
    protected BaseNavigationManager navigationManager;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ViewStub viewStub = view.findViewById(R.id.view_stub);
        viewStub.setLayoutResource(getLayoutResId());
        viewStub.inflate();
        unbinder = ButterKnife.bind(this, view);
        initView();
        viewModel = (viewModel) initializeViewModel();
        intializeDagger();
        callAPIService();
        navigationManager = BaseNavigationManager.getInstance();
        navigationManager.setCurrentActivity((BaseActivity) getActivity());
        return view;
    }

    private int getLayoutId() {
        int layoutId = 0;
        switch (getViewType()) {
            case BusinessConstants.FRAME:
                layoutId = R.layout.frame_layout;
                break;
            case BusinessConstants.SCROLLABLE:
                layoutId = R.layout.scrollable_layout;
                break;
        }
        return layoutId;
    }

    protected abstract int getLayoutResId();

    protected abstract ViewModel initializeViewModel();

    protected abstract int getViewType();

    protected abstract void initView();

    protected void subscribeLiveData() {
        viewModel.subscribeOnLoadingData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {

            }
        });

        viewModel.subscribeOnErrorData().observe(this, new Observer<ErrorModel>() {
            @Override
            public void onChanged(@Nullable ErrorModel errorModel) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract void callAPIService();

    protected abstract void intializeDagger();

}
