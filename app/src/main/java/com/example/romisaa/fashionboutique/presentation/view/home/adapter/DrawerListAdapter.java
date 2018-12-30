package com.example.romisaa.fashionboutique.presentation.view.home.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.romisaa.fashionboutique.R;

import java.util.HashMap;
import java.util.List;

public class DrawerListAdapter extends BaseExpandableListAdapter {

    private List<String> ListHeaders;
    private HashMap<String, List<String>> ListChildrenHeaders;
    private Context context;

    public DrawerListAdapter(List<String> ListHeaders,
                             HashMap<String, List<String>> ListChildrenHeaders,
                             Context context) {
        this.ListHeaders = ListHeaders;
        this.ListChildrenHeaders = ListChildrenHeaders;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return ListHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ListChildrenHeaders.get(ListHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return ListHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ListChildrenHeaders.get(ListHeaders.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_header, null);
        }

        TextView listTitleTextView = convertView.findViewById(R.id.header);
        AssetManager am = context.getApplicationContext().getAssets();

        Typeface typeface = Typeface.createFromAsset(am, "fonts/Caviar_Dreams_Bold.ttf");

        listTitleTextView.setTypeface(typeface);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_child, null);
        }
        TextView expandedListTextView = convertView.findViewById(R.id.header);

        AssetManager am = context.getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(am, "fonts/Caviar_Dreams_Bold.ttf");

        expandedListTextView.setTypeface(typeface);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
