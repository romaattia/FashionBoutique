package com.example.romisaa.fashionboutique.presentation.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.ErrorModel;

public abstract class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> subscribeOnLoadingData() {
        MutableLiveData<Boolean> handleLoading = null;
        return handleLoading;
    }

    public MutableLiveData<ErrorModel> subscribeOnErrorData() {
        MutableLiveData<ErrorModel> handleError = null;
        return handleError;
    }

}
