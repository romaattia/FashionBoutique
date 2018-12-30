package com.example.romisaa.fashionboutique.presentation.navigation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.romisaa.fashionboutique.data.model.AboutModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseActivity;
import com.example.romisaa.fashionboutique.utils.StringUtils;

import java.lang.ref.WeakReference;

public abstract class BaseNavigationManager {

    private static BaseNavigationManager navigationManager;

    protected FragmentManager fragmentManager;

    protected WeakReference<BaseActivity> currentActivity;


    public static BaseNavigationManager getInstance() {
        if (navigationManager == null) {
            navigationManager = new NavigationManager();
        }
        return navigationManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public BaseActivity getCurrentActivity() {
        return currentActivity.get();
    }

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = new WeakReference<>(currentActivity);
    }

    public void replaceFragment(int containerViewId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(int containerViewId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (StringUtils.isNull(tag)) {
            transaction.add(containerViewId, fragment);
        } else {
            transaction.add(containerViewId, fragment, tag);
        }

        if (addToBackStack) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

    public abstract void navigateToAddProductFragment(String groupType, String subGroupType);
    public abstract void navigateToViewProductFragment(String groupType, String subCategoryType);

    public abstract void navigateToSignupActivity();

    public abstract void navigateToHomeActivity();

    public abstract void navigateToFeedbackFragment();

    public abstract void navigateToAboutFragment();

    public abstract void navigateToeditAboutSection(AboutModel aboutModel);
}
