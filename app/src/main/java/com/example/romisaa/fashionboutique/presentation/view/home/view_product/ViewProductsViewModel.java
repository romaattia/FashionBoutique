package com.example.romisaa.fashionboutique.presentation.view.home.view_product;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.ViewProductsUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ViewProductsViewModel extends BaseViewModel {

    public MutableLiveData<List<ProductItemModel>> itemsliveData = new MutableLiveData<>();

    @Inject
    ViewProductsUseCase viewProductsUseCase;

    public ViewProductsViewModel(@NonNull Application application) {
        super(application);
    }

    public void getProductItems(String groupType, String subGroupType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(BusinessConstants.GROUPTYPE, groupType);
        parameters.put(BusinessConstants.SUBCATEGORYTYPE, subGroupType);
        viewProductsUseCase.execute(parameters, new DefaultObserver<List<ProductItemModel>>() {
            @Override
            public void onNext(List<ProductItemModel> model) {
                super.onNext(model);
                itemsliveData.setValue(model);
            }
        });
    }
}
