package com.johnhaines.boardmonkey.gamemaker;

/**
 * Created by johnhaines on 7/21/16.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class AdapterTreeKeyAndEditTextList extends BaseAdapter {
    private final ArrayList mData;

    public AdapterTreeKeyAndEditTextList(Map<ClassCharAttribute, Integer> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<ClassCharAttribute, Integer> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attribute_mods, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<ClassCharAttribute, Integer> item = getItem(position);

        TextView txtAttribute = ((TextView) result.findViewById(R.id.lblAttributeField));
        txtAttribute.setText(item.getKey().toString());
        EditText edtModField = ((EditText) result.findViewById(R.id.txtModField));
        edtModField.setText("" + item.getValue());


        return result;
    }
}
