package com.example.romisaa.fashionboutique.presentation.view.home.add_product;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Base64;

import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseViewModel;
import com.example.romisaa.fashionboutique.usecase.AddProductsUseCase;
import com.example.romisaa.fashionboutique.usecase.base.DefaultObserver;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class AddProductViewModel extends BaseViewModel {

    public MutableLiveData<Boolean> isProductAdded = new MutableLiveData<>();

    @Inject
    AddProductsUseCase addProductsUseCase;

    public AddProductViewModel(@NonNull Application application) {
        super(application);
    }

    public void addTopsProduct(String groupType, String subGroupType, Bitmap bitmap, String desc, String price) {
        ProductItemModel model = new ProductItemModel(1, encodeImage(bitmap), desc, price);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(BusinessConstants.GROUPTYPE, groupType);
        parameters.put(BusinessConstants.SUBCATEGORYTYPE, subGroupType);
        parameters.put(BusinessConstants.PRODUCT, model);
        addProductsUseCase.execute(parameters, new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                super.onNext(aBoolean);
                isProductAdded.setValue(true);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }
}
