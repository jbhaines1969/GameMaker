package com.johnhaines.boardmonkey.gamemaker;

/**
 * Created by johnhaines on 7/21/16.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class AdapterTreeIntegerKeyAndStringValue extends BaseAdapter {
    private final ArrayList mData;

    public AdapterTreeIntegerKeyAndStringValue(Map<Integer, String> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<Integer, String> getItem(int position) {
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
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_level_names, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<Integer, String> item = getItem(position);

        TextView txtLevelNumber = ((TextView) result.findViewById(R.id.txtLevelNumber));
        txtLevelNumber.setText(item.getKey().toString());
        TextView txtLevelName = ((TextView) result.findViewById(R.id.txtLevelName));
        txtLevelName.setText(item.getValue());

        return result;
    }
}
