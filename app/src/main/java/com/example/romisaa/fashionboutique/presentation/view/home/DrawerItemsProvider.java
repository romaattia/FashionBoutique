package com.example.romisaa.fashionboutique.presentation.view.home;

import com.example.romisaa.fashionboutique.utils.constants.BusinessConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawerItemsProvider {
    public static List<String> getNavigationListHeaders() {
        List<String> groups = new ArrayList<>();
        groups.add(BusinessConstants.CLOTHING_CATEGORY);
        groups.add(BusinessConstants.DRESSES_CATEGORY);
        groups.add(BusinessConstants.ACCCESSORIES_CATEGORY);
        groups.add(BusinessConstants.SHOES_CATEGORY);
        groups.add(BusinessConstants.BAGS_CATEGORY);
        groups.add(BusinessConstants.FEEDBACK_CATEGORY);
        groups.add(BusinessConstants.ABOUT_CATEGORY);
        return groups;
    }

    public static HashMap<String, List<String>> getNavigationListSubHeaders() {
        HashMap<String, List<String>> map = new HashMap<>();
        List<String> listHeaders = getNavigationListHeaders();
        map.put(listHeaders.get(0), getClothingSubGroup());
        map.put(listHeaders.get(1), getDressesSubGroup());
        map.put(listHeaders.get(2), getAccessoriesSubGroup());
        map.put(listHeaders.get(3), getShoesesSubGroup());
        map.put(listHeaders.get(4), getBagsSubGroup());
        map.put(listHeaders.get(5), new ArrayList<String>());
        map.put(listHeaders.get(6), new ArrayList<String>());
        return map;
    }

    private static List<String> getClothingSubGroup() {
        List<String> list = new ArrayList<>();
        list.add(BusinessConstants.TOPS);
        list.add(BusinessConstants.TROUSERS);
        list.add(BusinessConstants.JEANS);
        list.add(BusinessConstants.SKIRTS);
        return list;
    }

    private static List<String> getDressesSubGroup() {
        List<String> list = new ArrayList<>();
        list.add(BusinessConstants.MINI);
        list.add(BusinessConstants.MIDI);
        list.add(BusinessConstants.MAXI);
        return list;
    }

    private static List<String> getAccessoriesSubGroup() {
        List<String> list = new ArrayList<>();
        list.add(BusinessConstants.HAIR);
        list.add(BusinessConstants.BAGS);
        return list;
    }

    private static List<String> getShoesesSubGroup() {
        List<String> list = new ArrayList<>();
        list.add(BusinessConstants.BOOTS);
        list.add(BusinessConstants.HEELS);
        list.add(BusinessConstants.FLATS);
        list.add(BusinessConstants.SANDALS);
        return list;
    }

    private static List<String> getBagsSubGroup() {
        List<String> list = new ArrayList<>();
        list.add(BusinessConstants.SMALL);
        list.add(BusinessConstants.LARGE);
        list.add(BusinessConstants.BACK);
        return list;
    }
}




