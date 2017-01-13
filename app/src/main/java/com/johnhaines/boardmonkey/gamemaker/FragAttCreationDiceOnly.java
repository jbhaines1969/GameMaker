package com.johnhaines.boardmonkey.gamemaker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAttCreationDiceOnly extends Fragment implements View.OnClickListener {

    Button btnSaveDiceOnly;
    EditText edtDiceSides;
    EditText edtDiceRolls;

    public FragAttCreationDiceOnly() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_dice_only, container, false);

        btnSaveDiceOnly = (Button) rootView.findViewById(R.id.btnSaveDiceOnly);
        btnSaveDiceOnly.setOnClickListener(this);
        edtDiceSides = (EditText) rootView.findViewById(R.id.edtDiceSides);
        edtDiceSides.setText(String.valueOf(((GameApplication) getActivity().getApplication()).
                getGame().getAttCreation().getDiceSides()));
        edtDiceRolls = (EditText) rootView.findViewById(R.id.edtDiceRolls);
        edtDiceRolls.setText(String.valueOf(((GameApplication) getActivity().getApplication()).
                getGame().getAttCreation().getDiceRolls()));

        return rootView;
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
