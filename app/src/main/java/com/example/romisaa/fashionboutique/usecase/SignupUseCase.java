package com.example.romisaa.fashionboutique.usecase;

import com.example.romisaa.fashionboutique.data.DataRepository;
import com.example.romisaa.fashionboutique.usecase.base.UseCase;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class SignupUseCase extends UseCase<Boolean> {

    @Inject
    public SignupUseCase(CompositeDisposable compositeDisposable, DataRepository dataRepository) {
        super(compositeDisposable, dataRepository);
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable(Map<String, Object> parameters) {
        return dataRepository.signup(parameters);
    }
}
