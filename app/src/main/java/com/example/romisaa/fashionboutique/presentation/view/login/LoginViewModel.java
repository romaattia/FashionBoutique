package com.example.romisaa.fashionboutique.presentation.view.login;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.LoginUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isUserLogged = new MutableLiveData<>();

    @Inject
    LoginUseCase loginUseCase;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public void login(String userName, String pw) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(BusinessConstants.USERNAME, userName);
        parameters.put(BusinessConstants.PASSWORD, pw);
        loginUseCase.execute(parameters, new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                super.onNext(value);
                isUserLogged.setValue(value);
            }
        });
    }
}
