package com.example.romisaa.fashionboutique.presentation.view.login;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.base.BaseActivity;
import com.example.romisaa.fashionboutique.utils.StringUtils;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginViewModel> {

    private static int PERMISSIONS_REQUEST = 0;
    @BindView(R.id.username_et)
    AppCompatEditText usernameEt;
    @BindView(R.id.pw_et)
    AppCompatEditText pwEt;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.signup_bt)
    Button signupBt;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(LoginViewModel.class);
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

    public boolean isReadWritePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults != null && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private void login() {
        if (StringUtils.isNullEmpty(usernameEt.getText().toString())) {
            usernameEt.setError("This field can not be blank");
        }
        if (StringUtils.isNullEmpty(pwEt.getText().toString())) {
            pwEt.setError("This field can not be blank");
        }
        if (!StringUtils.isNullEmpty(usernameEt.getText().toString()) && !StringUtils.isNullEmpty(pwEt.getText().toString())) {
            viewModel.login(usernameEt.getText().toString(), pwEt.getText().toString());
            viewModel.isUserLogged.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean value) {
                    if (value) {
                        navigationManager.navigateToHomeActivity();
                    } else {
                        showDialog();
                    }
                }
            });
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage("Invalid User Name Or Password")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void signup() {
        navigationManager.navigateToSignupActivity();
    }

    @OnClick(R.id.login_bt)
    public void onLoginBtClicked() {
        if (isReadWritePermissionGranted()) {
            login();
        } else {
            isReadWritePermissionGranted();
        }
    }

    @OnClick(R.id.signup_bt)
    public void onSignupBtClicked() {
        if (isReadWritePermissionGranted()) {
            signup();
        } else {
            isReadWritePermissionGranted();
        }
    }
}
