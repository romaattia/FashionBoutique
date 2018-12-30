package com.example.romisaa.fashionboutique.usecase.base;


import com.example.romisaa.fashionboutique.data.DataRepository;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class UseCase<Model> {
    protected DataRepository dataRepository;
    private CompositeDisposable compositeDisposable;
    private Observable<Model> observable;
    private Disposable disposable;

    public UseCase(CompositeDisposable compositeDisposable, DataRepository dataRepository) {
        this.compositeDisposable = compositeDisposable;
        this.dataRepository = dataRepository;
    }

    public void disposeAll() {
        if (!this.compositeDisposable.isDisposed()) {
            this.compositeDisposable.dispose();
        }
    }

    public void dispose() {
        if (!this.compositeDisposable.isDisposed()) {
            this.compositeDisposable.remove(disposable);
        }
    }

    private void addDisposable(Disposable disposable) {
        if (disposable != null && this.compositeDisposable != null) {
            this.compositeDisposable.add(disposable);
        }
    }

    abstract protected Observable<Model> buildUseCaseObservable(Map<String, Object> parameters);

    public void execute(Map<String, Object> parameters, DisposableObserver<Model> observer) {
        if (observer != null) {
            final Observable<Model> observable = this.buildUseCaseObservable(parameters)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            addDisposable(observable.subscribeWith(observer));
        }
    }
}