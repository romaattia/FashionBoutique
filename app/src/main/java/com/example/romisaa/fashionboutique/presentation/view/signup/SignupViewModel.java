package com.example.romisaa.fashionboutique.presentation.view.signup;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.LoginUseCase;
import com.example.romisaa.fashionboutique.usecase.SignupUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class SignupViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isUserSignup = new MutableLiveData<>();

    @Inject
    SignupUseCase signupUseCase;

    public SignupViewModel(@NonNull Application application) {
        super(application);
    }

    public void signup(String userName, String pw) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(BusinessConstants.USERNAME, userName);
        parameters.put(BusinessConstants.PASSWORD, pw);
        signupUseCase.execute(parameters, new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                super.onNext(value);
                isUserSignup.setValue(value);
            }
        });
    }
}
