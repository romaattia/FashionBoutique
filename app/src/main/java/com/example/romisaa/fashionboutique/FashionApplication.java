package com.example.romisaa.fashionboutique;

import android.app.Application;
import android.content.Context;

import com.example.romisaa.fashionboutique.dagger.AppComponent;
import com.example.romisaa.fashionboutique.dagger.AppModule;
import com.example.romisaa.fashionboutique.dagger.DaggerAppComponent;

public class FashionApplication extends Application {

    private static Context context;
    private static AppComponent appComponent;

    public static Context getContext() {
        return context;
    }

    public static AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule((Application) FashionApplication.getContext())).build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
