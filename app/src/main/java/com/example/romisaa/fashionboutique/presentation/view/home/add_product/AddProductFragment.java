package com.example.romisaa.fashionboutique.presentation.view.home.add_product;


import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.ImageView;

import com.example.romisaa.fashionboutique.FashionApplication;
import com.example.romisaa.fashionboutique.R;
import com.example.romisaa.fashionboutique.presentation.base.BaseFragment;
import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class AddProductFragment extends BaseFragment<AddProductViewModel> {

    private static int PERMISSIONS_REQUEST = 0;
    private static int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.add_iv)
    ImageView addIv;
    @BindView(R.id.desc_tv)
    AppCompatEditText descTv;
    @BindView(R.id.price_tv)
    AppCompatEditText priceTv;
    @BindView(R.id.insert_bt)
    Button insertBt;

    private String groupType;
    private String subGroupType;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_add_product;
    }

    @Override
    protected ViewModel initializeViewModel() {
        return ViewModelProviders.of(this).get(AddProductViewModel.class);
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
    }

    @Override
    protected void callAPIService() {

    }

    @Override
    protected void intializeDagger() {
        FashionApplication.getComponent().inject(viewModel);
    }

    @OnClick(R.id.add_iv)
    public void onAddIvClicked() {
        openGallary();
    }

    @OnClick(R.id.insert_bt)
    public void onInsertBtClicked() {
        viewModel.addTopsProduct(groupType, subGroupType,
                ((BitmapDrawable) iv.getDrawable()).getBitmap(), descTv.getText().toString(), priceTv.getText().toString());

        viewModel.isProductAdded.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean value) {
                if (value) {
                    finishFragment();
                }
            }
        });
    }

    private void finishFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    public boolean isReadWritePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST);
                return false;
            }
        } else {
            return true;
        }
    }

    private void openGallary() {
        if (isReadWritePermissionGranted()) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        } else {
            isReadWritePermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            openGallary();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
