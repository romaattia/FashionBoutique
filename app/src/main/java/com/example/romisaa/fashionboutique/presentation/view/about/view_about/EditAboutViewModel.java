package com.example.romisaa.fashionboutique.presentation.view.about.view_about;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.EditAboutUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class EditAboutViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> aboutSectionInserted = new MutableLiveData<>();


    @Inject
    EditAboutUseCase editAboutUseCase;

    public EditAboutViewModel(@NonNull Application application) {
        super(application);
    }


    public void editABout(String desc, String page, String num) {
        Map<String, Object> parameters = new HashMap<>();
        AboutModel model = new AboutModel(desc, page, num);
        parameters.put(BusinessConstants.ABOUT, model);
        editAboutUseCase.execute(parameters, new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                super.onNext(aBoolean);
                aboutSectionInserted.setValue(true);
            }
        });
    }
}
