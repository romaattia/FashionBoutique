package com.example.romisaa.fashionboutique.data;


import com.example.romisaa.fashionboutique.data.local.LocalRepository;
import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.data.remote.RemoteRepository;
import com.example.romisaa.fashionboutique.utils.NetworkUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public class DataRepositoryImpl implements DataRepository {

    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    public DataRepositoryImpl(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }


    @Override
    public Observable<List<ProductItemModel>> getProductItems(Map<String, Object> parameters) {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.getProductItems(parameters);
        } else {
            return localRepository.getProductItems(parameters);
        }
    }

    @Override
    public Observable<Boolean> addProducts(Map<String, Object> parameters) {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.addProducts(parameters);
        } else {
            return localRepository.addProducts(parameters);
        }
    }

    @Override
    public Observable<Boolean> login(Map<String, Object> parameters) {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.login(parameters);
        } else {
            return localRepository.login(parameters);
        }
    }

    @Override
    public Observable<Boolean> signup(Map<String, Object> parameters) {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.signup(parameters);
        } else {
            return localRepository.signup(parameters);
        }
    }

    @Override
    public Observable<List<FeedbackModel>> getFeedbacks() {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.getFeedbacks();
        } else {
            return localRepository.getFeedbacks();
        }
    }

    @Override
    public Observable<AboutModel> getAboutSection() {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.getAboutSection();
        } else {
            return localRepository.getAboutSection();
        }
    }

    @Override
    public Observable<Boolean> editAboutSection(Map<String, Object> parameters) {
        if (NetworkUtils.isNetworkAvailable()) {
            return remoteRepository.editAboutSection(parameters);
        } else {
            return localRepository.editAboutSection(parameters);
        }
    }
}
