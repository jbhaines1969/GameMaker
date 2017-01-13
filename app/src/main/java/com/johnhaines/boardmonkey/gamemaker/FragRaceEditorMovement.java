package com.johnhaines.boardmonkey.gamemaker;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragRaceEditorMovement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragRaceEditorMovement extends Fragment implements View.OnClickListener {

    private Integer index;

    private TextView lblWalk;
    private TextView lblRun;
    private TextView lblFly;
    private TextView lblSwim;
    private EditText txtWalk;
    private EditText txtFly;
    private EditText txtSwim;
    private Button btnSave;

    public FragRaceEditorMovement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param listType Parameter 1.
     * @param index    Parameter 2.
     * @return A new instance of fragment FragRaceEditorMovement.
     */

    public static FragRaceEditorMovement newInstance(String listType, Integer index) {
        FragRaceEditorMovement fragment = new FragRaceEditorMovement();
        Bundle args = new Bundle();
        args.putString("listType", listType);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_race_editor_movement, container, false);

        lblWalk = (TextView) rootView.findViewById(R.id.lblWalk);
        lblFly = (TextView) rootView.findViewById(R.id.lblFly);
        lblSwim = (TextView) rootView.findViewById(R.id.lblSwim);
        txtWalk = (EditText) rootView.findViewById(R.id.txtWalk);
        txtWalk.setText(getWalkSpeed());
        txtFly = (EditText) rootView.findViewById(R.id.txtFly);
        txtFly.setText(getFlySpeed());
        txtSwim = (EditText) rootView.findViewById(R.id.txtSwim);
        txtSwim.setText(getSwimSpeed());
        btnSave = (Button) rootView.findViewById(R.id.btnSaveMovement);
        btnSave.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSave) {

            Integer walk = 0;
            Integer fly = 0;
            Integer swim = 0;

            try {
                walk = Integer.parseInt(txtWalk.getText().toString());
                fly = Integer.parseInt(txtFly.getText().toString());
                swim = Integer.parseInt(txtSwim.getText().toString());
            } catch (NumberFormatException e) {
                // TODO make toast warning about using integers for move speed.
            }

            ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).setMoveSpeed(walk);
            ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).setFlySpeed(fly);
            ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).setSwimSpeed(swim);

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }

    public String getWalkSpeed() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getMoveSpeed();
        String speedText = Integer.toString(speed);
        return speedText;
    }

    public String getFlySpeed() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getFlySpeed();
        String speedText = Integer.toString(speed);
        return speedText;
    }

    public String getSwimSpeed() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getSwimSpeed();
        String speedText = Integer.toString(speed);
        return speedText;
    }
}
