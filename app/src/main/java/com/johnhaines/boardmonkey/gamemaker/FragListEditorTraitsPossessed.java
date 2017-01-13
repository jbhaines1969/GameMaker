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
public class FragListEditorTraitsPossessed extends Fragment implements View.OnClickListener {

    private Integer index = 0;
    private String listType = "";

    private TextView lblPossessedTraitsItemName;
    private ListView lstPossessedTraits;
    private Button btnSavePossessedTraits;
    private ArrayAdapter listAdapter;

    public FragListEditorTraitsPossessed() {
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
        View rootView = inflater.inflate(R.layout.fragment_race_editor_race_traits, container, false);

        lblPossessedTraitsItemName = (TextView) rootView.findViewById(R.id.lblPossessedTraitsItemName);
        lblPossessedTraitsItemName.setText("Traits Possessed");
        lstPossessedTraits = (ListView) rootView.findViewById(R.id.lstRaceTraits);
        btnSavePossessedTraits = (Button) rootView.findViewById(R.id.btnSaveRaceTraits);
        btnSavePossessedTraits.setOnClickListener(this);
        listAdapter = makeListAdapter();
        lstPossessedTraits.setAdapter(listAdapter);

        selectItems();

        return rootView;

    }

    private void selectItems() {

        if (listType.equals("Races")) {
            for (ClassCharTrait item : ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).getTraits()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (item == listAdapter.getItem(i)) {
                        lstPossessedTraits.setItemChecked(i, true);
                    }
                }
            }
        }

        if (listType.equals("Classes")) {
            for (ClassCharTrait item : ((GameApplication) getActivity().
                    getApplication()).getGame().getClasses().get(index).getTraits()) {

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (item == listAdapter.getItem(i)) {
                        lstPossessedTraits.setItemChecked(i, true);
                    }
                }
            }
        }
    }

    private ArrayAdapter makeListAdapter() {

        ArrayAdapter listAdapter = new ArrayAdapter<ClassCharTrait>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getTraits()) {
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
        if (v == btnSavePossessedTraits) {

            if (listType.equals("Races")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getRaces().get(index).clearRaceTraits();

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (lstPossessedTraits.isItemChecked(i) == true) {
                        ClassCharTrait item = (ClassCharTrait) lstPossessedTraits.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getRaces().get(index).addTrait(item);
                    }
                }
            }

            if (listType.equals("Classes")) {
                ((GameApplication) getActivity().
                        getApplication()).getGame().getClasses().get(index).clearClassTraits();

                for (int i = 0; i < listAdapter.getCount(); i++) {
                    if (lstPossessedTraits.isItemChecked(i) == true) {
                        ClassCharTrait item = (ClassCharTrait) lstPossessedTraits.getItemAtPosition(i);
                        ((GameApplication) getActivity().
                                getApplication()).getGame().getClasses().get(index).addTrait(item);
                    }
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }

    }
}
