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
public class FragAttCreationDicePlusPoints extends Fragment implements View.OnClickListener {


    Button btnSaveDicePlusPoints;
    EditText edtPointsDicePlusPoints;
    EditText edtDiceSidesDicePlusPoints;
    EditText edtDiceRollsDicePlusPoints;


    public FragAttCreationDicePlusPoints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_dice_plus_points, container, false);

        btnSaveDicePlusPoints = (Button) rootView.findViewById(R.id.btnSaveDicePlusPoints);
        btnSaveDicePlusPoints.setOnClickListener(this);
        edtPointsDicePlusPoints = (EditText) rootView.findViewById(R.id.edtPointsDicePlusPoints);
        edtPointsDicePlusPoints.setText(String.valueOf(((GameApplication) getActivity().getApplication())
                .getGame().getAttCreation().getPoints()));
        edtDiceSidesDicePlusPoints = (EditText) rootView.findViewById(R.id.edtDiceSidesDicePlusPoints);
        edtDiceSidesDicePlusPoints.setText(String.valueOf(((GameApplication) getActivity().getApplication())
                .getGame().getAttCreation().getDiceSides()));
        edtDiceRollsDicePlusPoints = (EditText) rootView.findViewById(R.id.edtDiceRollsDicePlusPoints);
        edtDiceRollsDicePlusPoints.setText(String.valueOf(((GameApplication) getActivity().getApplication())
                .getGame().getAttCreation().getDiceRolls()));

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSaveDicePlusPoints) {

            int points;

            points = Integer.parseInt(edtDiceSidesDicePlusPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceSides(points);

            points = Integer.parseInt(edtDiceRollsDicePlusPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceRolls(points);

            points = Integer.parseInt(edtPointsDicePlusPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setPoints(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();

        }
    }
}
