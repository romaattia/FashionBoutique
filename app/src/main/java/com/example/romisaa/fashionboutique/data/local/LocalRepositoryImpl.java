package com.example.romisaa.fashionboutique.data.local;

import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LocalRepositoryImpl implements LocalRepository {

    PreferencesManager preferencesManager;
    DatabaseManager databaseManager;

    @Inject
    public LocalRepositoryImpl(PreferencesManager preferencesManager, DatabaseManager databaseManager) {
        this.preferencesManager = preferencesManager;
        this.databaseManager = databaseManager;
    }

    @Override
    public Observable<List<ProductItemModel>> getProductItems(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Observable<Boolean> addProducts(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Observable<Boolean> login(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Observable<Boolean> signup(Map<String, Object> parameters) {
        return null;
    }

    @Override
    public Observable<List<FeedbackModel>> getFeedbacks() {
        return null;
    }

    @Override
    public Observable<AboutModel> getAboutSection() {
        return null;
    }

    @Override
    public Observable<Boolean> editAboutSection(Map<String, Object> parameters) {
        return null;
    }
}
