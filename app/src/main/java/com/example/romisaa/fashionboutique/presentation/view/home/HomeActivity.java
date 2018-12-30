package com.example.romisaa.fashionboutique.presentation.view.home;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.base.BaseActivity;
import com.example.romisaa.fashionboutique.presentation.view.home.adapter.DrawerListAdapter;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeViewModel> {

    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.expandable_lv)
    ExpandableListView expandableLv;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private BaseExpandableListAdapter listAdapter;

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    protected @BusinessConstants.ViewType
    int getViewType() {
        return BusinessConstants.FRAME;
    }

    @Override
    public int getContainerId() {
        return R.id.content_frame;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initExpandableListView();
    }

    @Override
    protected void initView() {
        setupActionBar();
    }

    @Override
    protected void callAPIService() {

    }

    @Override
    protected void intializeDagger() {

    }

    private void initExpandableListView() {
        List<String> listDataHeader = DrawerItemsProvider.getNavigationListHeaders();
        HashMap<String, List<String>> listDataChild = DrawerItemsProvider.getNavigationListSubHeaders();
        listAdapter = new DrawerListAdapter(listDataHeader, listDataChild, this);
        expandableLv.setAdapter(listAdapter);
        expandableLv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 5) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navigationManager.navigateToFeedbackFragment();
                }
                if (groupPosition == 6) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    navigationManager.navigateToAboutFragment();
                }
                return false;
            }
        });
        expandableLv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                drawerLayout.closeDrawer(GravityCompat.START);
                String groupType = null;
                String subCategoryType = null;
                switch (groupPosition) {
                    case 0:
                        groupType = BusinessConstants.CLOTHING_CATEGORY;
                        subCategoryType = handleClothingGroup(childPosition);
                        break;
                    case 1:
                        groupType = BusinessConstants.DRESSES_CATEGORY;
                        subCategoryType = handleDressesGroup(childPosition);
                        break;
                    case 2:
                        groupType = BusinessConstants.ACCCESSORIES_CATEGORY;
                        subCategoryType = handleAccessoriesGroup(childPosition);
                        break;
                    case 3:
                        groupType = BusinessConstants.SHOES_CATEGORY;
                        subCategoryType = handleShoesGroup(childPosition);
                        break;
                    case 4:
                        groupType = BusinessConstants.BAGS_CATEGORY;
                        subCategoryType = handleBagsGroup(childPosition);
                        break;
                }
                navigationManager.navigateToViewProductFragment(groupType, subCategoryType);
                return false;
            }
        });
    }

    private String handleClothingGroup(int childPosition) {
        String subCategoryType = null;
        switch (childPosition) {
            case 0:
                subCategoryType = BusinessConstants.TOPS;
                break;
            case 1:
                subCategoryType = BusinessConstants.TROUSERS;
                break;
            case 2:
                subCategoryType = BusinessConstants.JEANS;
                break;
            case 3:
                subCategoryType = BusinessConstants.SKIRTS;
                break;
        }
        return subCategoryType;
    }

    private String handleDressesGroup(int childPosition) {
        String subCategoryType = null;
        switch (childPosition) {
            case 0:
                subCategoryType = BusinessConstants.MINI;
                break;
            case 1:
                subCategoryType = BusinessConstants.MIDI;
                break;
            case 2:
                subCategoryType = BusinessConstants.MAXI;
                break;
        }
        return subCategoryType;
    }

    private String handleAccessoriesGroup(int childPosition) {
        String subCategoryType = null;
        switch (childPosition) {
            case 0:
                subCategoryType = BusinessConstants.HAIR;
                break;
            case 1:
                subCategoryType = BusinessConstants.BAGS;
                break;
        }
        return subCategoryType;
    }

    private String handleShoesGroup(int childPosition) {
        String subCategoryType = null;
        switch (childPosition) {
            case 0:
                subCategoryType = BusinessConstants.BOOTS;
                break;
            case 1:
                subCategoryType = BusinessConstants.HEELS;
                break;
            case 2:
                subCategoryType = BusinessConstants.FLATS;
                break;
            case 3:
                subCategoryType = BusinessConstants.SANDALS;
                break;
        }
        return subCategoryType;
    }

    private String handleBagsGroup(int childPosition) {
        String subCategoryType = null;
        switch (childPosition) {
            case 0:
                subCategoryType = BusinessConstants.SMALL;
                break;
            case 1:
                subCategoryType = BusinessConstants.LARGE;
                break;
            case 2:
                subCategoryType = BusinessConstants.BACK;
                break;
        }
        return subCategoryType;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void subscribeLiveData() {
        super.subscribeLiveData();
    }

}
