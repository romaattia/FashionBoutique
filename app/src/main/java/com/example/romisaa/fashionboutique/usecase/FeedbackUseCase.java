package com.example.romisaa.fashionboutique.usecase;

import com.example.romisaa.fashionboutique.data.DataRepository;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.usecase.base.UseCase;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class FeedbackUseCase extends UseCase<List<FeedbackModel>> {

    @Inject
    public FeedbackUseCase(CompositeDisposable compositeDisposable, DataRepository dataRepository) {
        super(compositeDisposable, dataRepository);
    }

    @Override
    protected Observable<List<FeedbackModel>> buildUseCaseObservable(Map<String, Object> parameters) {
        return dataRepository.getFeedbacks();
    }

}
