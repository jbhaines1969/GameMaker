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
 * {@link FragClassEditorControlBar.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragClassEditorControlBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragClassEditorControlBar extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private String listType;
    private Integer index;

    private ClassGame game;

    private OnFragmentInteractionListener mListener;

    private TextView lblItemName;
    private ButtonNoClick btnEditClassDescription;
    private ButtonNoClick btnEditClassStartingPoints;
    private ButtonNoClick btnEditLevelUpPoints;
    private ButtonNoClick btnEditClassKnownSkills;
    private ButtonNoClick btnEditClassAttributeRequirements;
    private ButtonNoClick btnEditClassTraits;
    private ButtonNoClick btnEditClassLevelNames;

    public FragClassEditorControlBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragRaceEditorControlBar.
     */

    public static FragClassEditorControlBar newInstance(String listType, Integer index) {
        FragClassEditorControlBar fragment = new FragClassEditorControlBar();
        Bundle args = new Bundle();
        args.putString("listType", listType);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = ((GameApplication) getActivity().getApplication()).getGame();

        if (getArguments() != null) {
            index = getArguments().getInt("index");
            listType = getArguments().getString("listType");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_class_editor_control_bar, container, false);

        lblItemName = (TextView) rootView.findViewById(R.id.lblClassName);
        lblItemName.setText(((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getName());
        btnEditClassDescription = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassDescription);
        btnEditClassDescription.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassDescription);
        btnEditClassStartingPoints = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassStartingPoints);
        btnEditClassStartingPoints.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassStartingPoints);
        btnEditLevelUpPoints = (ButtonNoClick) rootView.findViewById(R.id.btnEditLevelUpPoints);
        btnEditLevelUpPoints.setOnClickListener(this);
        getPrimaryButtonImage(btnEditLevelUpPoints);
        btnEditClassKnownSkills = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassKnownSkills);
        btnEditClassKnownSkills.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassKnownSkills);
        btnEditClassAttributeRequirements = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassAttributeRequirements);
        btnEditClassAttributeRequirements.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassAttributeRequirements);
        btnEditClassTraits = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassTraits);
        btnEditClassTraits.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassTraits);
        btnEditClassLevelNames = (ButtonNoClick) rootView.findViewById(R.id.btnEditClassLevelNames);
        btnEditClassLevelNames.setOnClickListener(this);
        getPrimaryButtonImage(btnEditClassLevelNames);


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

        switch (game.getType()) {
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

        if (v == btnEditClassDescription) {
            message = "Description";
        }
        if (v == btnEditClassStartingPoints) {
            message = "Starting Points";
        }
        if (v == btnEditLevelUpPoints) {
            message = "Level Points";
        }
        if (v == btnEditClassKnownSkills) {
            message = "Skills";
        }
        if (v == btnEditClassAttributeRequirements) {
            message = "Attribute Requirements";
        }
        if (v == btnEditClassTraits) {
            message = "Traits";
        }
        if (v == btnEditClassLevelNames) {
            message = "Level Names";
        }


        if (mListener != null) {
            mListener.onClassEditorButtonClicked(message, index);
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
        void onClassEditorButtonClicked(String message, Integer index);
    }
}
