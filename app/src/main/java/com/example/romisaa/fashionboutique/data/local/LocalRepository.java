package com.example.romisaa.fashionboutique.data.local;

import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public interface LocalRepository {

    Observable<List<ProductItemModel>> getProductItems(Map<String, Object> parameters);

    Observable<Boolean> addProducts(Map<String,Object> parameters);

    Observable<Boolean> login(Map<String,Object> parameters);

    Observable<Boolean> signup(Map<String,Object> parameters);

    Observable<List<FeedbackModel>> getFeedbacks();
}
