package com.example.romisaa.fashionboutique.presentation.view.feedback;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.FeedbackUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;

import java.util.List;

import javax.inject.Inject;

public class FeedbackViewModel extends BaseViewModel {

    public MutableLiveData<List<FeedbackModel>> itemsliveData = new MutableLiveData<>();

    @Inject
    FeedbackUseCase feedbackUseCase;

    public FeedbackViewModel(@NonNull Application application) {
        super(application);
    }

    public void getFeedbacks() {
        feedbackUseCase.execute(null, new DefaultObserver<List<FeedbackModel>>() {
            @Override
            public void onNext(List<FeedbackModel> feedbackModels) {
                super.onNext(feedbackModels);
                itemsliveData.setValue(feedbackModels);
            }
        });
    }
}
