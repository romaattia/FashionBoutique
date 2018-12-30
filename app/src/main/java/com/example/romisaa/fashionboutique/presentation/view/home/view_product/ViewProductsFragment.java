package com.example.romisaa.fashionboutique.presentation.view.home.view_product;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.data.model.ProductItemModel;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.presentation.view.ItemClickListener;
import com.example.romisaa.fashionboutique.presentation.view.home.adapter.ItemsRecyclarViewAdapter;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class ViewProductsFragment extends BaseFragment<ViewProductsViewModel> implements ItemClickListener {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ItemsRecyclarViewAdapter adapter;
    private String groupType;
    private String subGroupType;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_view_products;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(ViewProductsViewModel.class);
    }

    @Override
    protected int getViewType() {
        return BusinessConstants.FRAME;
    }

    @Override
    protected void initView() {
        if (getArguments() != null) {
            Bundle args = getArguments();
            if (args.containsKey(BusinessConstants.GROUPTYPE) && args.containsKey(BusinessConstants.SUBCATEGORYTYPE)) {
                groupType = (String) args.get(BusinessConstants.GROUPTYPE);
                subGroupType = (String) args.get(BusinessConstants.SUBCATEGORYTYPE);
            }
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(layoutManager);
        adapter = new ItemsRecyclarViewAdapter(this);
        rv.setAdapter(adapter);
    }

    @Override
    protected void callAPIService() {
        viewModel.getProductItems(groupType, subGroupType);
        viewModel.itemsliveData.observe(this, new Observer<List<ProductItemModel>>() {
            @Override
            public void onChanged(@Nullable List<ProductItemModel> productItemModels) {
                adapter.setData(productItemModels);
            }
        });
    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        navigationManager.navigateToAddProductFragment(groupType, subGroupType);
    }


    @Override
    public void onItemClick() {

    }
}
