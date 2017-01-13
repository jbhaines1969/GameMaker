package com.johnhaines.boardmonkey.gamemaker;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragRaceEditorAttributeMods extends Fragment implements View.OnClickListener {

    private Integer index = 0;

    private TextView lblAttributeModsRaceName;
    private ListView lstAttributeMods;
    private Button btnSaveAttributeMods;
    private AdapterTreeKeyAndEditTextList listAdapter;

    public FragRaceEditorAttributeMods() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt("index");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        index = getArguments().getInt("index");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_race_editor_attribute_mods, container, false);

        lblAttributeModsRaceName = (TextView) rootView.findViewById(R.id.lblAttributeModsRaceName);
        lblAttributeModsRaceName.setText("Attribute Modifiers");
        lstAttributeMods = (ListView) rootView.findViewById(R.id.lstAttributeMods);
        btnSaveAttributeMods = (Button) rootView.findViewById(R.id.btnSaveAttributeMods);
        btnSaveAttributeMods.setOnClickListener(this);
        listAdapter = makeListAdapter();
        lstAttributeMods.setAdapter(listAdapter);

        return rootView;
    }

    private AdapterTreeKeyAndEditTextList makeListAdapter() {

        AdapterTreeKeyAndEditTextList listAdapter = new AdapterTreeKeyAndEditTextList(((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getRaceAttributeModsMale());


        return listAdapter;
    }

    private String getRaceName(Integer index) {

        String name = ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).toString();

        return name;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSaveAttributeMods) {

            int mod = 0;

            for (int i = 0; i < listAdapter.getCount(); i++) {

                Map.Entry<ClassCharAttribute, Integer> item = listAdapter.getItem(i);

                View view = lstAttributeMods.getChildAt(i);
                EditText modField = (EditText) view.findViewById(R.id.txtModField);

                String string = modField.getText().toString();

                if (isNumeric(string)) {
                    mod = Integer.parseInt(string);

                    ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).
                            addAttributeModMale(item.getKey(), mod);
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
