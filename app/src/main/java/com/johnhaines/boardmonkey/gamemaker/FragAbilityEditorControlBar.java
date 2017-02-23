package com.johnhaines.boardmonkey.gamemaker;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAbilityEditorControlBar extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private TextView lblAbilityName;
    private ButtonNoClick btnDescription;
    private ButtonNoClick btnPrefClasses;
    private ButtonNoClick btnPrefRaces;
    private ButtonNoClick btnEffectType;
    private int index;
    private String listType;
    private ClassGame game;

    private OnFragmentInteractionListener mListener;


    public FragAbilityEditorControlBar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = ((GameApplication) getActivity().getApplication()).getGame();

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt("index");
            listType = savedInstanceState.getString("listType");
        } else if (getArguments() != null) {
            index = getArguments().getInt("index");
            listType = getArguments().getString("listType");
        } else {
            index = 0;
            listType = "";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ability_editor_control_bar, container, false);

        lblAbilityName = (TextView) rootView.findViewById(R.id.lblNameAbilityEditorControlBar);
        lblAbilityName.setText(getAbilityName());
        btnDescription = (ButtonNoClick) rootView.findViewById(R.id.btnEditAbilityDescription);
        btnDescription.setOnClickListener(this);
        btnPrefClasses = (ButtonNoClick) rootView.findViewById(R.id.btnEditAbilityPreferredClasses);
        btnPrefClasses.setOnClickListener(this);
        btnPrefRaces = (ButtonNoClick) rootView.findViewById(R.id.btnEditAbilityPreferredRaces);
        btnPrefRaces.setOnClickListener(this);
        btnEffectType = (ButtonNoClick) rootView.findViewById(R.id.btnEditAbilityEffectType);
        btnEffectType.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragClassEditorControlBar.OnFragmentInteractionListener) {
            mListener = (FragAbilityEditorControlBar.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof FragClassEditorControlBar.OnFragmentInteractionListener) {
            mListener = (FragAbilityEditorControlBar.OnFragmentInteractionListener) context;
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
    public void onClick(View view) {

        if (view instanceof ButtonNoClick) {
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

        if (view == btnDescription) {
            message = "Description";
        }

        if (view == btnEffectType) {
            message = "Effect";
        }

        if (view == btnPrefClasses) {
            message = "Classes";
        }

        if (view == btnPrefRaces) {
            message = "Races";
        }

        if (mListener != null) {
            mListener.onAbilityEditorButtonClicked(message, index);
        }
    }

    private String getAbilityName() {

        String name = "";

        switch (listType) {
            case ("Skills"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getSkills().get(index).getName();
                break;
            case ("Traits"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getTraits().get(index).getName();
                break;
            case ("Features"):
                name = ((GameApplication) getActivity().getApplication()).getGame().getFeatures().get(index).getName();
                break;
            default:
                break;
        }

        return name;
    }

    public interface OnFragmentInteractionListener {
        void onAbilityEditorButtonClicked(String message, Integer index);
    }

}
