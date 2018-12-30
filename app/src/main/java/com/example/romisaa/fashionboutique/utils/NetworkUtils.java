package com.example.romisaa.fashionboutique.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.romisaa.fashionboutique.FashionApplication;

public class NetworkUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) FashionApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
