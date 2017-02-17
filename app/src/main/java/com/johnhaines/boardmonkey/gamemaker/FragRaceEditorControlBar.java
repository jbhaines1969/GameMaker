package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
public class FragRaceEditorControlBar extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private String listType;
    private Integer index;

    private OnFragmentInteractionListener mListener;

    private TextView lblItemName;
    private ButtonNoClick btnEditRaceDescription;
    private ButtonNoClick btnEditRaceMovement;
    private ButtonNoClick btnEditAllowedClasses;
    private ButtonNoClick btnEditRaceKnownSkills;
    private ButtonNoClick btnEditRaceAttributeMods;
    private ButtonNoClick btnEditRaceTraits;
    private ButtonNoClick btnEditRaceAttributeRequirements;

    public FragRaceEditorControlBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragRaceEditorControlBar.
     */

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

        lblItemName = (TextView) rootView.findViewById(R.id.lblRaceName);
        lblItemName.setText(((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getName());
        btnEditRaceDescription = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceDescription);
        btnEditRaceDescription.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceDescription);
        btnEditRaceMovement = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceMovement);
        btnEditRaceMovement.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceMovement);
        btnEditAllowedClasses = (ButtonNoClick) rootView.findViewById(R.id.btnEditAllowedClasses);
        btnEditAllowedClasses.setOnClickListener(this);
        getPrimaryButtonImage(btnEditAllowedClasses);
        btnEditRaceKnownSkills = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceKnownSkills);
        btnEditRaceKnownSkills.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceKnownSkills);
        btnEditRaceAttributeMods = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceAttributeMods);
        btnEditRaceAttributeMods.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceAttributeMods);
        btnEditRaceTraits = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceTraits);
        btnEditRaceTraits.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceTraits);
        btnEditRaceAttributeRequirements = (ButtonNoClick) rootView.findViewById(R.id.btnEditRaceAttributeRequirements);
        btnEditRaceAttributeRequirements.setOnClickListener(this);
        getPrimaryButtonImage(btnEditRaceAttributeRequirements);

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

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (((GameApplication) getActivity().getApplication()).getGame().getType()) {
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
            case ("Mixed"):
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.selector_button_fantasy_primary));
                break;
        }
    }

    public void playSound(int currenSoundID) {

        MediaPlayer mPlayer = MediaPlayer.create(getActivity(), currenSoundID);

        mPlayer.setVolume(1, 1);
        mPlayer.setLooping(false);
        mPlayer.setOnCompletionListener(this);
        mPlayer.start();

    }

    @Override
    public void onCompletion(MediaPlayer mPlayer) {
        mPlayer.reset();
        mPlayer.release();
    }

    @Override
    public void onClick(View v) {

        if (v instanceof ButtonNoClick) {
            String gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();

            int soundID = 0;
            switch (gameType) {
                case ("Sci-Fi"):
                    soundID = R.raw.syfi_hit;
                    break;
                case ("Fantasy"):
                    soundID = R.raw.fan_hit;
                    break;
                case ("Military"):
                    soundID = R.raw.mil_hit;
                    break;
                case ("Mixed"):
                    soundID = R.raw.fan_hit;
                    break;
            }
            playSound(soundID);
        }

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
        if (v == btnEditRaceKnownSkills) {
            message = "Skills";
        }
        if (v == btnEditRaceAttributeMods) {
            message = "AttributeMods";
        }
        if (v == btnEditRaceTraits) {
            message = "Traits";
        }
        if (v == btnEditRaceAttributeRequirements) {
            message = "Attribute Requirements";
        }


        if (mListener != null) {
            mListener.onRaceEditorButtonClicked(message, index);
        }
    }

    public void updateIndex(Integer newIndex) {
        index = newIndex;
        lblItemName.setText(((GameApplication) getActivity().getApplication()).getGame().getRaces().get(index).getName());
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

        void onRaceEditorButtonClicked(String message, Integer index);
    }
}
