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

public class AdapterTreeKeyAndTwoEditTextList extends BaseAdapter {
    private final ArrayList mDataMin;
    private final ArrayList mDataMax;

    public AdapterTreeKeyAndTwoEditTextList(Map<ClassCharAttribute, Integer> map1, Map<ClassCharAttribute, Integer> map2) {
        mDataMin = new ArrayList();
        mDataMin.addAll(map1.entrySet());
        mDataMax = new ArrayList();
        mDataMax.addAll(map2.entrySet());
    }

    @Override
    public int getCount() {
        return mDataMin.size();
    }

    @Override
    public Map.Entry<ClassCharAttribute, Integer> getItem(int position) {

        return (Map.Entry) mDataMin.get(position);
    }

    public Map.Entry<ClassCharAttribute, Integer> getItemMin(int position) {
        return (Map.Entry) mDataMin.get(position);
    }

    public Map.Entry<ClassCharAttribute, Integer> getItemMax(int position) {
        return (Map.Entry) mDataMax.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attribute_requirements, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<ClassCharAttribute, Integer> itemMin = getItemMin(position);
        Map.Entry<ClassCharAttribute, Integer> itemMax = getItemMax(position);

        TextView txtAttribute = ((TextView) result.findViewById(R.id.lblAttributeField));
        txtAttribute.setText(itemMin.getKey().toString());
        EditText edtMinField = ((EditText) result.findViewById(R.id.txtMinField));
        edtMinField.setText("" + itemMin.getValue());
        EditText edtMaxField = ((EditText) result.findViewById(R.id.txtMaxField));
        edtMaxField.setText("" + itemMax.getValue());


        return result;
    }
}
