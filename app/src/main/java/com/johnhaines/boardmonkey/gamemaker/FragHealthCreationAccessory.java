package com.johnhaines.boardmonkey.gamemaker;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragHealthCreationAccessory extends Fragment implements View.OnClickListener {

    private CheckBox chkModifiedByAtts;
    private ListView lstAttList;

    public FragHealthCreationAccessory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_health_creation_accessory, container, false);

        chkModifiedByAtts = (CheckBox) rootView.findViewById(R.id.chkModifiedByChar);
        lstAttList = (ListView) rootView.findViewById(R.id.lstHealthCreationAttList);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view == chkModifiedByAtts) {
            if (chkModifiedByAtts.isChecked()) {
                lstAttList.setVisibility(View.INVISIBLE);
                lstAttList.setClickable(false);
            } else {
                lstAttList.setVisibility(View.VISIBLE);
                lstAttList.setClickable(true);
            }
        }
    }
}
