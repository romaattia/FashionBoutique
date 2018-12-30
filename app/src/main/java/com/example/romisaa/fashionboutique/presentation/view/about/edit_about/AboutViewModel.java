package com.example.romisaa.fashionboutique.presentation.view.about.edit_about;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.AboutUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;

import javax.inject.Inject;

public class AboutViewModel extends BaseViewModel {

    public MutableLiveData<AboutModel> aboutLiveData = new MutableLiveData<>();

    @Inject
    AboutUseCase aboutUseCase;

    public AboutViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAboutSection() {
        aboutUseCase.execute(null, new DefaultObserver<AboutModel>() {
            @Override
            public void onNext(AboutModel aboutModel) {
                super.onNext(aboutModel);
                aboutLiveData.setValue(aboutModel);
            }
        });
    }
}
