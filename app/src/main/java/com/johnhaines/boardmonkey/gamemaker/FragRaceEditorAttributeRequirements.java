package com.johnhaines.boardmonkey.gamemaker;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
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
public class FragRaceEditorAttributeRequirements extends Fragment implements View.OnClickListener {

    private Integer index = 0;

    private TextView lblAttributeModsRaceName;
    private TextView lblAttribute;
    private TextView lblMinMax;
    private ListView lstAttributeMods;
    private Button btnSaveAttributeMods;
    private AdapterTreeKeyAndTwoEditTextList listAdapter;

    public FragRaceEditorAttributeRequirements() {
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
        View rootView = inflater.inflate(R.layout.fragment_race_editor_attribute_requirements, container, false);

        lblAttributeModsRaceName = (TextView) rootView.findViewById(R.id.lblAttributeModsRaceName);
        lblAttributeModsRaceName.setText("Attribute Limits");
        lblAttribute = (TextView) rootView.findViewById(R.id.lblAttribute);
        lblMinMax = (TextView) rootView.findViewById(R.id.lblMinMax);
        lstAttributeMods = (ListView) rootView.findViewById(R.id.lstAttributeMods);
        btnSaveAttributeMods = (Button) rootView.findViewById(R.id.btnSaveAttributeMods);
        btnSaveAttributeMods.setOnClickListener(this);
        listAdapter = makeListAdapter();
        lstAttributeMods.setAdapter(listAdapter);


        return rootView;
    }

    private AdapterTreeKeyAndTwoEditTextList makeListAdapter() {

        AdapterTreeKeyAndTwoEditTextList listAdapter = new AdapterTreeKeyAndTwoEditTextList(((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getMinAttributes(), ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getMaxAttributes());


        return listAdapter;
    }

    @Override
    public void onClick(View v) {
        if (v == btnSaveAttributeMods) {

            int minMod = 0;
            int maxMod = 0;

            for (int i = 0; i < listAdapter.getCount(); i++) {

                Map.Entry<ClassCharAttribute, Integer> itemMin = listAdapter.getItemMin(i);
                Map.Entry<ClassCharAttribute, Integer> itemMax = listAdapter.getItemMax(i);

                View view = lstAttributeMods.getChildAt(i);
                EditText minField = (EditText) view.findViewById(R.id.txtMinField);
                EditText maxField = (EditText) view.findViewById(R.id.txtMaxField);

                String stringMin = minField.getText().toString();
                String stringMax = maxField.getText().toString();

                if (isNumeric(stringMin)) {
                    minMod = Integer.parseInt(stringMin);

                    ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).
                            addMinAttribute(itemMin.getKey(), minMod);
                }

                if (isNumeric(stringMax)) {

                    maxMod = Integer.parseInt(stringMax);

                    if (maxMod < minMod) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Invalid Values: Maximum cannot be lower than minimum.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        break;
                    } else {
                        ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).
                                addMaxAttribute(itemMax.getKey(), maxMod);
                    }
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
