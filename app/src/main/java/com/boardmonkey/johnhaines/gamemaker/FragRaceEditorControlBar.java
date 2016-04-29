package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragRaceEditorControlBar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragRaceEditorControlBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragRaceEditorControlBar extends Fragment implements View.OnClickListener{

    private String listType;
    private Integer index;

    private OnFragmentInteractionListener mListener;

    private TextView lblItemName;
    private Button btnEditRaceDescription;
    private Button btnEditRaceMovement;
    private Button btnEditAllowedClasses;
    private Button btnEditRaceAutoFeatures;
    private Button btnEditRaceKnownSkills;
    private Button btnEditRaceAttributeMods;

    public FragRaceEditorControlBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FragRaceEditorControlBar.
     */
    // TODO: Rename and change types and number of parameters
    public static FragRaceEditorControlBar newInstance(String listType, Integer index) {
        FragRaceEditorControlBar fragment = new FragRaceEditorControlBar();
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
            listType = getArguments().getString("listType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_race_editor_control_bar, container, false);

        lblItemName = (TextView) rootView.findViewById(R.id.lblItemName);
        lblItemName.setText(((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getName());
        btnEditRaceDescription = (Button) rootView.findViewById(R.id.btnEditRaceDescription);
        btnEditRaceDescription.setOnClickListener(this);
        btnEditRaceMovement = (Button) rootView.findViewById(R.id.btnEditRaceMovement);
        btnEditRaceMovement.setOnClickListener(this);
        btnEditAllowedClasses = (Button) rootView.findViewById(R.id.btnEditAllowedClasses);
        btnEditAllowedClasses.setOnClickListener(this);
        btnEditRaceAutoFeatures = (Button) rootView.findViewById(R.id.btnEditRaceAutoFeatures);
        btnEditRaceAutoFeatures.setOnClickListener(this);
        btnEditRaceKnownSkills = (Button) rootView.findViewById(R.id.btnEditRaceKnownSkills);
        btnEditRaceKnownSkills.setOnClickListener(this);
        btnEditRaceAttributeMods = (Button) rootView.findViewById(R.id.btnEditRaceAttributeMods);
        btnEditRaceAttributeMods.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        String message = "";

        if (v == btnEditRaceDescription) {
            message = "Description";
        }
        if (v == btnEditRaceMovement) {
            message = "Movement";
        }
        if (v == btnEditAllowedClasses) {
            message = "Classes";
        }
        if (v == btnEditRaceAutoFeatures) {
            message = "Features";
        }
        if (v == btnEditRaceKnownSkills) {
            message = "Skills";
        }
        if (v == btnEditRaceAttributeMods) {
            message = "AttributeMods";
        }

        if (mListener != null) {
            mListener.onRaceEditorButtonClicked(message, index);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRaceEditorButtonClicked(String message, Integer index);
    }
}
