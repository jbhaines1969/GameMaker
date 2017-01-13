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
public class FragRaceEditorClassesAllowed extends Fragment implements View.OnClickListener {

    private Integer index = 0;

    private TextView lblRClassesRaceName;
    private ListView lstClassesAllowed;
    private Button btnSaveClassesAllowed;
    private ArrayAdapter listAdapter;


    public FragRaceEditorClassesAllowed() {
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
        View rootView = inflater.inflate(R.layout.fragment_race_editor_classes_allowed, container, false);

        lblRClassesRaceName = (TextView) rootView.findViewById(R.id.lblPossessedTraitsItemName);
        lblRClassesRaceName.setText("Allowed Classes");
        lstClassesAllowed = (ListView) rootView.findViewById(R.id.lstClassesAllowed);
        btnSaveClassesAllowed = (Button) rootView.findViewById(R.id.btnSaveClassesAllowed);
        btnSaveClassesAllowed.setOnClickListener(this);
        listAdapter = makeListAdapter();
        lstClassesAllowed.setAdapter(listAdapter);

        selectItems();

        return rootView;

    }

    private void selectItems() {

        for (ClassCharClass item : ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).getAllowedClasses()) {

            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (item == listAdapter.getItem(i)) {
                    lstClassesAllowed.setItemChecked(i, true);
                }
            }
        }
    }

    private final ArrayAdapter<ClassCharClass> makeListAdapter() {

        ArrayAdapter listAdapter = new ArrayAdapter<ClassCharClass>(
                getActivity(), android.R.layout.simple_list_item_multiple_choice, ((GameApplication) getActivity().
                getApplication()).getGame().getClasses()) {
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

    private String getRaceName(Integer index) {

        String name = ((GameApplication) getActivity().
                getApplication()).getGame().getRaces().get(index).toString();

        return name;

    }


    @Override
    public void onClick(View v) {
        if (v == btnSaveClassesAllowed) {

            ((GameApplication) getActivity().
                    getApplication()).getGame().getRaces().get(index).clearClassesAllowed();
            for (int i = 0; i < listAdapter.getCount(); i++) {
                if (lstClassesAllowed.isItemChecked(i) == true) {
                    ClassCharClass item = (ClassCharClass) lstClassesAllowed.getItemAtPosition(i);
                    ((GameApplication) getActivity().
                            getApplication()).getGame().getRaces().get(index).addClass(item);
                }
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }

    }
}
