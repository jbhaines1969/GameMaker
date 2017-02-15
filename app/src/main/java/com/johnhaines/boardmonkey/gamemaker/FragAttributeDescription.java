package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * .
 * Use the {@link FragAttributeDescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragAttributeDescription extends Fragment implements View.OnClickListener {

    private String listType;
    private Integer index;

    private EditText txtCharacteristicName;
    private EditText txtCharacteristicDescription;
    private ButtonNoClick btnSaveDescription;

    private String gameType;

    public FragAttributeDescription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragCharacteristicDescription.
     */

    public static FragAttributeDescription newInstance(String listType, Integer index) {
        FragAttributeDescription fragment = new FragAttributeDescription();
        Bundle args = new Bundle();
        args.putString("lisType", listType);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            listType = getArguments().getString("listType");
            index = getArguments().getInt("index");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        listType = getArguments().getString("listType");
        index = getArguments().getInt("index");

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_attribute_description, container, false);

        txtCharacteristicName = (EditText) rootView.findViewById(R.id.txtCharacteristicName);
        txtCharacteristicName.setText(getName());
        txtCharacteristicDescription = (EditText) rootView.findViewById(R.id.txtCharacteristicDescription);
        txtCharacteristicDescription.setText(getDescription());
        btnSaveDescription = (ButtonNoClick) rootView.findViewById(R.id.btnSaveDescription);
        btnSaveDescription.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveDescription);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (gameType) {
            case ("Fantasy"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            case ("Sci-Fi"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
        }
    }

    @Override
    public void onClick(View v) {

        if (v == btnSaveDescription) {

            String description = txtCharacteristicDescription.getText().toString();
            String name = txtCharacteristicName.getText().toString();

            if (listType.equals("Attributes")) {
                ((GameApplication) getActivity().getApplication()).getGame().getAttributes().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getAttributes().get(index).setDescription(description);
            }
            if (listType.equals("Races")) {
                ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).setDescription(description);
            }
            if (listType.equals("Classes")) {
                ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setDescription(description);
            }
            if (listType.equals("Skills")) {
                ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).setDescription(description);
            }
            if (listType.equals("Traits")) {
                ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).setDescription(description);
            }
            if (listType == "Features") {
                ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).setName(name);
                ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).setDescription(description);
            }

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }

    private String getName() {

        String name = "";

        if (listType.equals("Attributes")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getAttributes().get(index).getName();
        }
        if (listType.equals("Races")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getName();
        }
        if (listType.equals("Classes")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getName();
        }
        if (listType.equals("Skills")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).getName();
        }
        if (listType.equals("Traits")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).getName();
        }
        if (listType.equals("Features")) {
            name = ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).getName();
        }

        return name;

    }

    private String getDescription() {

        String description = "";

        if (listType.equals("Attributes")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getAttributes().get(index).getDescription();
        }
        if (listType.equals("Races")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getDescription();
        }
        if (listType.equals("Classes")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getDescription();
        }
        if (listType.equals("Skills")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).getDescription();
        }
        if (listType.equals("Traits")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).getDescription();
        }
        if (listType.equals("Features")) {
            description = ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).getDescription();
        }

        return description;

    }

    public void updateIndex(Integer newIndex) {
        index = newIndex;
        txtCharacteristicName.setText(getName());
        txtCharacteristicDescription.setText(getDescription());

    }
}
