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
public class FragAttCreationBasePoints extends Fragment implements View.OnClickListener {

    Button btnSaveBasePoints;
    EditText edtBasePointsOnly;


    public FragAttCreationBasePoints() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_base_points, container, false);

        edtBasePointsOnly = (EditText) rootView.findViewById(R.id.edtBasePointsOnly);
        edtBasePointsOnly.setText(String.valueOf(((GameApplication) getActivity().getApplication()).
                getGame().getAttCreation().getBaseScore()));
        edtBasePointsOnly.setSelectAllOnFocus(true);
        btnSaveBasePoints = (Button) rootView.findViewById(R.id.btnSaveBasePoints);
        btnSaveBasePoints.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSaveBasePoints) {

            int points = Integer.parseInt(edtBasePointsOnly.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setBaseScore(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();

        }

    }
}
