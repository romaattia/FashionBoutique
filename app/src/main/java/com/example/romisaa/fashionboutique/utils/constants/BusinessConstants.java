package com.example.romisaa.fashionboutique.utils.constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BusinessConstants {

    public static final int FRAME = 0;
    public static final int SCROLLABLE = 1;
    public static final String CLOTHING_CATEGORY = "Clothing";
    public static final String DRESSES_CATEGORY = "Dresses";
    public static final String ACCCESSORIES_CATEGORY = "Accessories";
    public static final String SHOES_CATEGORY = "Shoes";
    public static final String BAGS_CATEGORY = "Bags";
    public static final String FEEDBACK_CATEGORY = "Feedback";
    public static final String ABOUT_CATEGORY = "About";
    public static final String TOPS = "Tops";
    public static final String TROUSERS = "Trousers";
    public static final String JEANS = "Jeans";
    public static final String SKIRTS = "Skirts";
    public static final String MINI = "Mini";
    public static final String MIDI = "Midi";
    public static final String MAXI = "Maxi";
    public static final String HAIR = "Hair";
    public static final String BAGS = "Bags";
    public static final String BOOTS = "Boots";
    public static final String HEELS = "Heels";
    public static final String FLATS = "Flats";
    public static final String SANDALS = "Sandals";
    public static final String SMALL = "Small";
    public static final String LARGE = "Large";
    public static final String BACK = "Back";
    public static final String PRODUCT = "tops_product";
    public static final String SUBCATEGORYTYPE = "subCategoryType";
    public static final String GROUPTYPE = "groupType";
    public static final String USERNAME = "UserName";
    public static final String PASSWORD = "Password" ;
    public static final String ABOUT ="about" ;

    @IntDef({FRAME, SCROLLABLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewType {
    }
}
