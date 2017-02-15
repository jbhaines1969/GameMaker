package com.johnhaines.boardmonkey.gamemaker;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAttCreationDiceOnly extends Fragment implements View.OnClickListener {

    private ButtonNoClick btnSaveDiceOnly;
    private EditText edtDiceSides;
    private EditText edtDiceRolls;

    private String gameType;

    public FragAttCreationDiceOnly() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_dice_only, container, false);

        btnSaveDiceOnly = (ButtonNoClick) rootView.findViewById(R.id.btnSaveDiceOnly);
        btnSaveDiceOnly.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveDiceOnly);
        edtDiceSides = (EditText) rootView.findViewById(R.id.edtDiceSides);
        edtDiceSides.setText(String.valueOf(((GameApplication) getActivity().getApplication()).
                getGame().getAttCreation().getDiceSides()));
        edtDiceRolls = (EditText) rootView.findViewById(R.id.edtDiceRolls);
        edtDiceRolls.setText(String.valueOf(((GameApplication) getActivity().getApplication()).
                getGame().getAttCreation().getDiceRolls()));

        return rootView;
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

        if (v == btnSaveDiceOnly) {

            int points;

            points = Integer.parseInt(edtDiceSides.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceSides(points);

            points = Integer.parseInt(edtDiceRolls.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceRolls(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();

        }
    }
}
