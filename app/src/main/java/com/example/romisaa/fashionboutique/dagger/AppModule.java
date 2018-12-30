package com.example.romisaa.fashionboutique.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.romisaa.fashionboutique.data.DataRepository;
import com.example.romisaa.fashionboutique.data.DataRepositoryImpl;
import com.example.romisaa.fashionboutique.data.local.DatabaseManager;
import com.example.romisaa.fashionboutique.data.local.LocalRepository;
import com.example.romisaa.fashionboutique.data.local.LocalRepositoryImpl;
import com.example.romisaa.fashionboutique.data.local.PreferencesManager;
import com.example.romisaa.fashionboutique.data.remote.RemoteRepository;
import com.example.romisaa.fashionboutique.data.remote.RemoteRepositoryImpl;
import com.example.romisaa.fashionboutique.utils.constants.PrefrenceConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Context getContext() {
        return this.application;
    }

    @Provides
    public CompositeDisposable getCompositDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    public DataRepository getDataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        return new DataRepositoryImpl(remoteRepository, localRepository);
    }

    @Provides
    public RemoteRepository getRemoteRepository() {
        return new RemoteRepositoryImpl();
    }

    @Provides
    public LocalRepository getLocalRepository(PreferencesManager preferencesManager, DatabaseManager databaseManager) {
        return new LocalRepositoryImpl(preferencesManager, databaseManager);
    }

    @Provides
    public PreferencesManager getPreferencesManager(SharedPreferences sharedPreferences) {
        return new PreferencesManager(sharedPreferences);
    }

    @Provides
    public DatabaseManager getDataManger() {
        return new DatabaseManager();
    }

    @Singleton
    @Provides
    public SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PrefrenceConstants.SHARED_PREFRENCE_FILE, Context.MODE_PRIVATE);
    }

}
