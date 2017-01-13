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
public class FragAttCreationBasePlusDice extends Fragment implements View.OnClickListener {

    Button btnSaveBasePlusDice;
    EditText edtBasePointsBasePlusDice;
    EditText edtDiceSidesBasePlusDice;
    EditText edtDiceRollsBasePlusDice;


    public FragAttCreationBasePlusDice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_base_plus_dice, container, false);

        btnSaveBasePlusDice = (Button) rootView.findViewById(R.id.btnSaveBasePlusDice);
        btnSaveBasePlusDice.setOnClickListener(this);
        edtBasePointsBasePlusDice = (EditText) rootView.findViewById(R.id.edtBasePointsBasePlusDice);
        edtBasePointsBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getBaseScore()));
        edtDiceSidesBasePlusDice = (EditText) rootView.findViewById(R.id.edtDiceSidesBasePlusDice);
        edtDiceSidesBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getDiceSides()));
        edtDiceRollsBasePlusDice = (EditText) rootView.findViewById(R.id.edtDiceRollsBasePlusDice);
        edtDiceRollsBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getDiceRolls()));

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSaveBasePlusDice) {

            int points;

            points = Integer.parseInt(edtDiceSidesBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceSides(points);

            points = Integer.parseInt(edtDiceRollsBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceRolls(points);

            points = Integer.parseInt(edtBasePointsBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setBaseScore(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
