package com.example.romisaa.fashionboutique.data.remote;

import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public interface RemoteRepository {

    Observable<List<ProductItemModel>> getProductItems(Map<String, Object> parameters);

    Observable<Boolean> addProducts(Map<String,Object> parameters);

    Observable<Boolean> login(Map<String,Object> parameters);

    Observable<Boolean> signup(Map<String,Object> parameters);

    Observable<List<FeedbackModel>> getFeedbacks();
}
