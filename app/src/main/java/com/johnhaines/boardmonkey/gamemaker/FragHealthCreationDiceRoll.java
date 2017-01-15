package com.johnhaines.boardmonkey.gamemaker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragHealthCreationDiceRoll extends Fragment implements View.OnClickListener {

    private Button btnSave;

    public FragHealthCreationDiceRoll() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_health_creation_dice_roll, container, false);

        btnSave = (Button) rootView.findViewById(R.id.btnSaveHealthDice);
        btnSave.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view == btnSave) {
            ((ActHealthCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
