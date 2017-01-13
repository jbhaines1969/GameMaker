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
public class FragAttCreationPointAssignment extends Fragment implements View.OnClickListener {

    Button btnSavePointAssignment;
    EditText edtPointAssignment;

    public FragAttCreationPointAssignment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_point_assignment, container, false);

        edtPointAssignment = (EditText) rootView.findViewById(R.id.edtPointAssignment);
        edtPointAssignment.setText(String.valueOf(((GameApplication) getActivity().getApplication())
                .getGame().getAttCreation().getPoints()));
        edtPointAssignment.setSelectAllOnFocus(true);
        btnSavePointAssignment = (Button) rootView.findViewById(R.id.btnSavePointAssignment);
        btnSavePointAssignment.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSavePointAssignment) {

            int points = Integer.parseInt(edtPointAssignment.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setPoints(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
