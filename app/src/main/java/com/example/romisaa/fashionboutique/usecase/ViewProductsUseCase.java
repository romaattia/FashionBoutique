package com.example.romisaa.fashionboutique.usecase;

import com.example.romisaa.fashionboutique.data.DataRepository;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.usecase.base.UseCase;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

public class ViewProductsUseCase extends UseCase<List<ProductItemModel>> {

    @Inject
    public ViewProductsUseCase(CompositeDisposable compositeDisposable, DataRepository dataRepository) {
        super(compositeDisposable, dataRepository);
    }

    @Override
    protected Observable<List<ProductItemModel>> buildUseCaseObservable(Map<String, Object> parameters) {
        return dataRepository.getProductItems(parameters);
    }
}
