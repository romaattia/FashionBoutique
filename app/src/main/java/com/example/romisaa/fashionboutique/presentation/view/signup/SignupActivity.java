package com.example.romisaa.fashionboutique.presentation.view.signup;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.base.BaseActivity;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.BindView;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity<SignupViewModel> {

    @BindView(R.id.username_et)
    AppCompatEditText usernameEt;
    @BindView(R.id.pw_et)
    AppCompatEditText pwEt;
    @BindView(R.id.confirm_pw_et)
    AppCompatEditText confirmPwEt;
    @BindView(R.id.signup_bt)
    Button signupBt;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_signup;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(SignupViewModel.class);
    }

    @Override
    protected int getViewType() {
        return BusinessConstants.FRAME;
    }

    @Override
    public int getContainerId() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void callAPIService() {

    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }

    @OnClick(R.id.signup_bt)
    public void onViewClicked() {
        viewModel.signup(usernameEt.getText().toString(), pwEt.getText().toString());
        viewModel.isUserSignup.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    navigationManager.navigateToHomeActivity();
                }
            }
        });
    }
}
