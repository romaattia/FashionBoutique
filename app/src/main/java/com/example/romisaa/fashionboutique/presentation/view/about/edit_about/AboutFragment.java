package com.example.romisaa.fashionboutique.presentation.view.about.edit_about;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.BindView;
import butterknife.OnClick;


public class AboutFragment extends BaseFragment<AboutViewModel> {

    @BindView(R.id.desc_tv)
    TextView descTv;
    @BindView(R.id.page_url_tv)
    TextView pageUrlTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;

    private AboutModel aboutModel;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(AboutViewModel.class);
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
        viewModel.getAboutSection();
        viewModel.aboutLiveData.observe(this, new Observer<AboutModel>() {
            @Override
            public void onChanged(@Nullable AboutModel model) {
                if (model != null) {
                    aboutModel = model;
                    descTv.setText(model.getDescription());
                    pageUrlTv.setText(model.getPage());
                    phoneTv.setText(model.getNumber());
                }
            }
        });
    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }


    @OnClick(R.id.fab)
    public void onViewClicked() {
        navigationManager.navigateToeditAboutSection(aboutModel);
    }

}
