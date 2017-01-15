package com.johnhaines.boardmonkey.gamemaker;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragHealthCreationAccessory extends Fragment implements View.OnClickListener {

    private CheckBox chkModifiedByAtts;
    private ListView lstAttList;
    private Button btnSaveButton;
    private ArrayAdapter listAdapter;

    public FragHealthCreationAccessory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_health_creation_accessory, container, false);

        chkModifiedByAtts = (CheckBox) rootView.findViewById(R.id.chkModifiedByChar);
        chkModifiedByAtts.setOnClickListener(this);
        chkModifiedByAtts.setChecked(isModifiedChecked());
        lstAttList = (ListView) rootView.findViewById(R.id.lstHealthCreationAccAttList);
        listAdapter = makeListAdapter();
        lstAttList.setAdapter(listAdapter);
        setSelectedAttributes();
        setListAvailable();
        btnSaveButton = (Button) rootView.findViewById(R.id.btnSaveHealthAccessory);
        btnSaveButton.setOnClickListener(this);

        return rootView;
    }

    private void setListAvailable() {
        if (chkModifiedByAtts.isChecked()) {
            lstAttList.setVisibility(View.VISIBLE);
        } else {
            lstAttList.setVisibility(View.INVISIBLE);
        }
    }

    private boolean isModifiedChecked() {
        return ((GameApplication) getActivity().getApplication()).getGame().getHitsType().isModifiedByAtts();
    }

    @Override
    public void onClick(View view) {
        if (view == chkModifiedByAtts) {
            setListAvailable();
        }

        if (view == btnSaveButton) {

            ((GameApplication) getActivity().getApplication()).getGame().getHitsType().setModifiedByAtts(chkModifiedByAtts.isChecked());

            if (chkModifiedByAtts.isChecked()) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getHitsType().getAttList().clear();

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (lstAttList.isItemChecked(i)) {
                        ClassCharAttribute att = (ClassCharAttribute) lstAttList.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getHitsType().addAttribute(att);
                    }
                }
            }

            ((ActHealthCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }

    private ArrayAdapter makeListAdapter() {

        listAdapter = new ArrayAdapter<ClassCharAttribute>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getAttributes()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                return view;
            }
        };

        return listAdapter;
    }

    public void setSelectedAttributes() {

        for (ClassCharAttribute item : ((GameApplication) getActivity().
                getApplication()).getGame().getHitsType().getAttList()) {

            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (item == listAdapter.getItem(i)) {
                    lstAttList.setItemChecked(i, true);
                }
            }
        }
    }
}
