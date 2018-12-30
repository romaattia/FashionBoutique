package com.example.romisaa.fashionboutique.presentation.view.about;


import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;


public class AboutFragment extends BaseFragment<AboutViewModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(AndroidViewModel.class);
    }

    @Override
    protected int getViewType() {
        return BusinessConstants.SCROLLABLE;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void callAPIService() {

    }

    @Override
    protected void intializeDagger() {

    }

}
