package com.example.romisaa.fashionboutique.presentation.navigation;

import android.content.Intent;
import android.os.Bundle;

import com.example.romisaa.fashionboutique.presentation.view.feedback.FeedbackFragment;
import com.example.romisaa.fashionboutique.presentation.view.home.HomeActivity;
import com.example.romisaa.fashionboutique.presentation.view.home.add_product.AddProductFragment;
import com.example.romisaa.fashionboutique.presentation.view.home.view_product.ViewProductsFragment;
import com.example.romisaa.fashionboutique.presentation.view.signup.SignupActivity;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

public class NavigationManager extends BaseNavigationManager {

    @Override
    public void navigateToAddProductFragment(String groupType, String subGroupType) {
        AddProductFragment addProductFragment = new AddProductFragment();
        Bundle args = new Bundle();
        args.putString(BusinessConstants.GROUPTYPE, groupType);
        args.putString(BusinessConstants.SUBCATEGORYTYPE, subGroupType);
        addProductFragment.setArguments(args);
        addFragment(getCurrentActivity().getContainerId(), addProductFragment, true, null);
    }

    @Override
    public void navigateToViewProductFragment(String groupType, String subCategoryType) {
        ViewProductsFragment viewProductsFragment = new ViewProductsFragment();
        Bundle args = new Bundle();
        args.putString(BusinessConstants.GROUPTYPE, groupType);
        args.putString(BusinessConstants.SUBCATEGORYTYPE, subCategoryType);
        viewProductsFragment.setArguments(args);
        addFragment(getCurrentActivity().getContainerId(), viewProductsFragment, true, null);
    }

    @Override
    public void navigateToSignupActivity() {
        Intent intent = new Intent(getCurrentActivity(), SignupActivity.class);
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void navigateToHomeActivity() {
        Intent intent = new Intent(getCurrentActivity(), HomeActivity.class);
        getCurrentActivity().startActivity(intent);
    }

    @Override
    public void navigateToFeedbackFragment() {
        addFragment(getCurrentActivity().getContainerId(), new FeedbackFragment(), true, null);
    }

    @Override
    public void navigateToAboutFragment() {
        addFragment(getCurrentActivity().getContainerId(), null, true, null);
    }
}
