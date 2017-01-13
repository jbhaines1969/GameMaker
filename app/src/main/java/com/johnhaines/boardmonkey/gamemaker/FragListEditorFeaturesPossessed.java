package com.johnhaines.boardmonkey.gamemaker;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragListEditorFeaturesPossessed extends Fragment implements View.OnClickListener {

    private Integer index = 0;
    private String listType = "";

    private TextView lblFeaturesPossessedItemName;
    private ListView lstFeaturesPossessed;
    private Button btnSaveFeaturesPossessed;
    private ArrayAdapter listAdapter;


    public FragListEditorFeaturesPossessed() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt("index");
            listType = getArguments().getString("listType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        listType = getArguments().getString("listType");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_race_editor_features_possessed, container, false);

        lblFeaturesPossessedItemName = (TextView) rootView.findViewById(R.id.lblFeaturesPossessedItemName);
        lblFeaturesPossessedItemName.setText("Features Possessed");
        lstFeaturesPossessed = (ListView) rootView.findViewById(R.id.lstFeaturesPossessed);
        btnSaveFeaturesPossessed = (Button) rootView.findViewById(R.id.btnSaveFeaturesPossessed);
        btnSaveFeaturesPossessed.setOnClickListener(this);
        listAdapter = makeListAdapter();
        lstFeaturesPossessed.setAdapter(listAdapter);

        selectItems();

        return rootView;
    }

    private void selectItems() {

        if (listType.equals("Races")) {
            for (ClassCharFeature feature : ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).getAutoFeats()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (feature == listAdapter.getItem(i)) {
                        lstFeaturesPossessed.setItemChecked(i, true);
                    }
                }
            }
        }

        if (listType.equals("Classes")) {
            for (ClassCharFeature feature : ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).getClassFeats()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (feature == listAdapter.getItem(i)) {
                        lstFeaturesPossessed.setItemChecked(i, true);
                    }
                }
            }
        }
    }

    private final ArrayAdapter<ClassCharFeature> makeListAdapter() {

        ArrayAdapter listAdapter = new ArrayAdapter<ClassCharFeature>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getFeatures()) {
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

    private String getItemName(Integer index) {

        String name = "";

        if (listType.equals("Races")) {
            name = ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).toString();
        }

        if (listType.equals("Classes")) {
            name = ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).toString();
        }

        return name;
    }


    @Override
    public void onClick(View v) {
        if (v == btnSaveFeaturesPossessed) {

            if (listType.equals("Races")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getRaces().get(index).clearAutoFeatures();
            }

            if (listType.equals("Classes")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getClasses().get(index).clearClassFeatures();
            }
            for (int i = 0; i < listAdapter.getCount(); i++) {

                if (listType.equals("Races")) {
                    if (lstFeaturesPossessed.isItemChecked(i) == true) {
                        ClassCharFeature item = (ClassCharFeature) lstFeaturesPossessed.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getRaces().get(index).addAutoFeat(item);
                    }
                }

                if (listType.equals("Classes")) {
                    if (lstFeaturesPossessed.isItemChecked(i) == true) {
                        ClassCharFeature item = (ClassCharFeature) lstFeaturesPossessed.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getClasses().get(index).addClassFeat(item);
                    }
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }
}
