package com.example.romisaa.fashionboutique.presentation.base;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.ErrorModel;
import com.example.romisaa.fashionboutique.presentation.navigation.BaseNavigationManager;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<viewModel extends BaseViewModel> extends AppCompatActivity {

    protected viewModel viewModel;
    protected BaseNavigationManager navigationManager;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ViewStub viewStub = findViewById(R.id.view_stub);
        viewStub.setLayoutResource(getLayoutResId());
        viewStub.inflate();
        unbinder = ButterKnife.bind(this);
        initView();
        viewModel = (viewModel) initializeViewModel();
        intializeDagger();
        callAPIService();
        navigationManager = BaseNavigationManager.getInstance();
        navigationManager.setFragmentManager(getSupportFragmentManager());
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

    public abstract int getContainerId();

    protected abstract void initView();

    protected abstract void callAPIService();

    protected abstract void intializeDagger();

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
    protected void onStart() {
        super.onStart();
        navigationManager.setCurrentActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
