package com.johnhaines.boardmonkey.gamemaker;


import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragClassEditorStartingPoints#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragClassEditorStartingPoints extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index;

    private EditText edtStartingSkills;
    private EditText edtStartingTraits;
    private EditText edtStartingFeatures;
    private EditText edtHealthDice;
    private ButtonNoClick btnSave;

    public FragClassEditorStartingPoints() {
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

    public static FragClassEditorStartingPoints newInstance(String listType, Integer index) {
        FragClassEditorStartingPoints fragment = new FragClassEditorStartingPoints();
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

        View rootView = inflater.inflate(R.layout.fragment_class_editor_starting_points, container, false);

        edtHealthDice = (EditText) rootView.findViewById(R.id.edtHealthDice);
        edtHealthDice.setText(getHealthDiceSides());
        edtStartingSkills = (EditText) rootView.findViewById(R.id.edtStartingSkills);
        edtStartingSkills.setText(getSkillPoints());
        edtStartingTraits = (EditText) rootView.findViewById(R.id.edtStartingTraits);
        edtStartingTraits.setText(getTraitPoints());
        edtStartingFeatures = (EditText) rootView.findViewById(R.id.edtStartingFeatures);
        edtStartingFeatures.setText(getFeaturePoints());
        btnSave = (ButtonNoClick) rootView.findViewById(R.id.btnSaveStartingPoints);
        btnSave.setOnClickListener(this);
        getPrimaryButtonImage(btnSave);

        return rootView;
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

        if (v == btnSave) {

            Integer skillPoints = 0;
            Integer traitPoints = 0;
            Integer featurePoints = 0;
            Integer healthDie = 0;

            try {
                healthDie = Integer.parseInt(edtHealthDice.getText().toString());
                skillPoints = Integer.parseInt(edtStartingSkills.getText().toString());
                traitPoints = Integer.parseInt(edtStartingTraits.getText().toString());
                featurePoints = Integer.parseInt(edtStartingFeatures.getText().toString());
            } catch (NumberFormatException e) {
                // TODO make toast warning about using integers for points.
            }

            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setHealthDiceSides(healthDie);
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setStartingSkillPoints(skillPoints);
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setStartingTraitPoints(traitPoints);
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setStartingFeatPoints(featurePoints);

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();

        }
    }

    public String getHealthDiceSides() {
        Integer sides = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getHealthDiceSides();
        String healthDiceText = Integer.toString(sides);
        return healthDiceText;
    }

    public String getSkillPoints() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getStartingSkillPoints();
        String skillPointsText = Integer.toString(speed);
        return skillPointsText;
    }

    public String getTraitPoints() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getStartingTraitPoints();
        String traitPointsText = Integer.toString(speed);
        return traitPointsText;
    }

    public String getFeaturePoints() {
        Integer speed = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getStartingFeatPoints();
        String featPointsText = Integer.toString(speed);
        return featPointsText;
    }
}
