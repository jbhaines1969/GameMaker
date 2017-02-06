package com.johnhaines.boardmonkey.gamemaker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAbilityEditorControlBar extends Fragment implements View.OnClickListener {

    private TextView lblAbilityName;
    private Button btnDescription;
    private Button btnPrefClasses;
    private Button btnPrefRaces;
    private Button btnEffectType;
    private int index;
    private String listType;


    public FragAbilityEditorControlBar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index");
            listType = savedInstanceState.getString("listType");
        } else if (getArguments() != null) {
            index = getArguments().getInt("index");
            listType = getArguments().getString("listType");
        } else {
            index = 0;
            listType = "";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ability_editor_control_bar, container, false);

        lblAbilityName = (TextView) rootView.findViewById(R.id.lblNameAbilityEditorControlBar);
        lblAbilityName.setText(getAbilityName());
        btnDescription = (Button) rootView.findViewById(R.id.btnEditAbilityDescription);
        btnDescription.setOnClickListener(this);
        btnPrefClasses = (Button) rootView.findViewById(R.id.btnEditAbilityPreferredClasses);
        btnPrefClasses.setOnClickListener(this);
        btnPrefRaces = (Button) rootView.findViewById(R.id.btnEditAbilityPreferredRaces);
        btnPrefRaces.setOnClickListener(this);
        btnEffectType = (Button) rootView.findViewById(R.id.btnEditAbilityEffectType);
        btnEffectType.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

    }

    private String getAbilityName() {

        String name = "";

        switch (listType) {
            case ("Skills"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).getName();
                break;
            case ("Traits"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).getName();
                break;
            case ("Features"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).getName();
                break;
            default:
                break;
        }


        return name;
    }

}
