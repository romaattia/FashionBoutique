package com.example.romisaa.fashionboutique.presentation.view.about.view_about;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.BindView;
import butterknife.OnClick;

public class EditAboutFragment extends BaseFragment<EditAboutViewModel> {

    @BindView(R.id.desc_et)
    AppCompatEditText descEt;
    @BindView(R.id.page_url_et)
    AppCompatEditText pageUrlEt;
    @BindView(R.id.phone_et)
    AppCompatEditText phoneEt;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_edit_about;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(EditAboutViewModel.class);
    }

    @Override
    protected int getViewType() {
        return BusinessConstants.SCROLLABLE;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(BusinessConstants.ABOUT)) {
                AboutModel model = (AboutModel) args.getParcelable(BusinessConstants.ABOUT);
                if (model != null) {
                    if (model.getDescription() != null) descEt.setText(model.getDescription());
                    if (model.getPage() != null) pageUrlEt.setText(model.getPage());
                    if (model.getNumber() != null) phoneEt.setText(model.getNumber());
                }
            }
        }
    }

    @Override
    protected void callAPIService() {

    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        viewModel.editABout(descEt.getText().toString(), pageUrlEt.getText().toString(), phoneEt.getText().toString());
        viewModel.aboutSectionInserted.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }
}
