package com.example.romisaa.fashionboutique.data.remote;

import android.support.annotation.NonNull;

import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.data.model.FeedbackModel;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.data.model.UserModel;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class RemoteRepositoryImpl implements RemoteRepository {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Inject
    public RemoteRepositoryImpl() {
    }

    @Override
    public Observable<List<ProductItemModel>> getProductItems(Map<String, Object> parameters) {
        String groupType = (String) parameters.get(BusinessConstants.GROUPTYPE);
        String subGroupType = (String) parameters.get(BusinessConstants.SUBCATEGORYTYPE);
        final DatabaseReference ref = database.getReference(groupType + "/" + subGroupType);
        Observable<List<ProductItemModel>> observable = Observable.create(new ObservableOnSubscribe<List<ProductItemModel>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<ProductItemModel>> emitter) throws Exception {
                final List<ProductItemModel> list = new ArrayList<>();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ProductItemModel model = postSnapshot.getValue(ProductItemModel.class);
                            list.add(model);
                        }
                        emitter.onNext(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return observable;
    }

    @Override
    public Observable<Boolean> addProducts(Map<String, Object> parameters) {
        String groupType = (String) parameters.get(BusinessConstants.GROUPTYPE);
        String subGroupType = (String) parameters.get(BusinessConstants.SUBCATEGORYTYPE);
        final DatabaseReference ref = database.getReference(groupType + "/" + subGroupType);
        String key = ref.push().getKey();
        ProductItemModel model = (ProductItemModel) parameters.get(BusinessConstants.PRODUCT);
        ref.child(key).setValue(model);
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(true);
            }
        });
        return observable;
    }

    @Override
    public Observable<Boolean> login(final Map<String, Object> parameters) {
        final String username = (String) parameters.get(BusinessConstants.USERNAME);
        final String pw = (String) parameters.get(BusinessConstants.PASSWORD);
        final DatabaseReference ref = database.getReference("Users");
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> emitter) throws Exception {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Boolean userLogged = false;
                        if (dataSnapshot == null) {
                            emitter.onNext(userLogged);
                        } else {
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                UserModel model = postSnapshot.getValue(UserModel.class);
                                if (model.getUsername().equalsIgnoreCase(username) && model.getPassword().equalsIgnoreCase(pw)) {
                                    userLogged = true;
                                    break;
                                }
                            }
                            emitter.onNext(userLogged);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return observable;
    }

    @Override
    public Observable<Boolean> signup(Map<String, Object> parameters) {
        final String username = (String) parameters.get(BusinessConstants.USERNAME);
        final String pw = (String) parameters.get(BusinessConstants.PASSWORD);
        final DatabaseReference ref = database.getReference("Users");
        String key = ref.push().getKey();
        UserModel model = new UserModel(username, pw);
        ref.child(key).setValue(model);
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(true);
            }
        });
        return observable;
    }

    @Override
    public Observable<List<FeedbackModel>> getFeedbacks() {
        final DatabaseReference ref = database.getReference("Feedbacks");
        Observable<List<FeedbackModel>> observable = Observable.create(new ObservableOnSubscribe<List<FeedbackModel>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<FeedbackModel>> emitter) throws Exception {
                final List<FeedbackModel> list = new ArrayList<>();
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            FeedbackModel model = postSnapshot.getValue(FeedbackModel.class);
                            list.add(model);
                        }
                        emitter.onNext(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return observable;
    }

    @Override
    public Observable<AboutModel> getAboutSection() {
        final DatabaseReference ref = database.getReference("About/AboutModel");
        Observable<AboutModel> observable = Observable.create(new ObservableOnSubscribe<AboutModel>() {
            @Override
            public void subscribe(final ObservableEmitter<AboutModel> emitter) throws Exception {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        AboutModel model = dataSnapshot.getValue(AboutModel.class);
                        emitter.onNext(model);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return observable;
    }

    @Override
    public Observable<Boolean> editAboutSection(Map<String, Object> parameters) {
        AboutModel model = (AboutModel) parameters.get(BusinessConstants.ABOUT);
        final DatabaseReference ref = database.getReference("About/AboutModel");
        ref.setValue(model);
        Observable<Boolean> observable = Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                emitter.onNext(true);
            }
        });
        return observable;
    }
}
