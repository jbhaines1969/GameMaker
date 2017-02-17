package com.johnhaines.boardmonkey.gamemaker;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragClassEditorLevelUpPoints extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private int index = 0;

    private TextView lblClassNameLevelUpScreen;
    private ButtonNoClick btnSaveLevelUpPoints;
    private EditText edtLevelUpAttributesLevels;
    private EditText edtLevelUpAttributesPoints;
    private EditText edtLevelUpSkillLevels;
    private EditText edtLevelUpSkillPoints;
    private EditText edtLevelUpTraitLevels;
    private EditText edtLevelUpTraitPoints;
    private EditText edtLevelUpFeaturesLevels;
    private EditText edtLevelUpFeaturesPoints;

    private String gameType;

    public FragClassEditorLevelUpPoints() {
        // Required empty public constructor
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
        index = getArguments().getInt("index");

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_class_editor_level_up_points, container, false);

        lblClassNameLevelUpScreen = (TextView) rootView.findViewById(R.id.lblClassNameLevelUpScreen);
        lblClassNameLevelUpScreen.setText("Point Gains per Level");
        btnSaveLevelUpPoints = (ButtonNoClick) rootView.findViewById(R.id.btnSaveLevelUpPoints);
        btnSaveLevelUpPoints.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveLevelUpPoints);
        edtLevelUpAttributesLevels = (EditText) rootView.findViewById(R.id.edtLevelUpAttributesLevels);
        edtLevelUpAttributesLevels.setText(getEntry("AttributeLevels"));
        edtLevelUpAttributesPoints = (EditText) rootView.findViewById(R.id.edtLevelUpAttributesPoints);
        edtLevelUpAttributesPoints.setText(getEntry("AttributePoints"));
        edtLevelUpSkillLevels = (EditText) rootView.findViewById(R.id.edtLevelUpSkillLevels);
        edtLevelUpSkillLevels.setText(getEntry("SkillLevels"));
        edtLevelUpSkillPoints = (EditText) rootView.findViewById(R.id.edtLevelUpSkillPoints);
        edtLevelUpSkillPoints.setText(getEntry("SkillPoints"));
        edtLevelUpTraitLevels = (EditText) rootView.findViewById(R.id.edtLevelUpTraitLevels);
        edtLevelUpTraitLevels.setText(getEntry("TraitLevels"));
        edtLevelUpTraitPoints = (EditText) rootView.findViewById(R.id.edtLevelUpTraitPoints);
        edtLevelUpTraitPoints.setText(getEntry("TraitPoints"));
        edtLevelUpFeaturesLevels = (EditText) rootView.findViewById(R.id.edtLevelUpFeaturesLevels);
        edtLevelUpFeaturesLevels.setText(getEntry("FeatureLevels"));
        edtLevelUpFeaturesPoints = (EditText) rootView.findViewById(R.id.edtLevelUpFeaturesPoints);
        edtLevelUpFeaturesPoints.setText(getEntry("FeaturePoints"));

        return rootView;
    }

    private String getEntry(String valueType) {

        int value = 0;
        String returnValue = "";

        if (valueType.equals("AttributeLevels")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getLevelsForAttributePoints();
        }
        if (valueType.equals("AttributePoints")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getAttributePointsPerLevelUp();
        }
        if (valueType.equals("SkillLevels")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getLevelsForSkillPoints();
        }
        if (valueType.equals("SkillPoints")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getSkillPointsPerLevelUp();
        }
        if (valueType.equals("TraitLevels")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getLevelsForTraitPoints();
        }
        if (valueType.equals("TraitPoints")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getTraitPointsPerLevelUp();
        }
        if (valueType.equals("FeatureLevels")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getLevelsForFeatPoints();
        }
        if (valueType.equals("FeaturePoints")) {
            value = ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).getFeatPointsPerLevelUp();
        }

        return Integer.toString(value);
    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (gameType) {
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

        if (v == btnSaveLevelUpPoints) {

            int newValue;

            newValue = Integer.parseInt(edtLevelUpAttributesLevels.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setLevelsForAttributePoints(newValue);

            newValue = Integer.parseInt(edtLevelUpAttributesPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setAttributePointsPerLevelUp(newValue);

            newValue = Integer.parseInt(edtLevelUpSkillLevels.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setLevelsForSkillPoints(newValue);

            newValue = Integer.parseInt(edtLevelUpSkillPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setSkillPointsPerLevelUp(newValue);

            newValue = Integer.parseInt(edtLevelUpTraitLevels.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setLevelsForTraitPoints(newValue);

            newValue = Integer.parseInt(edtLevelUpTraitPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setTraitPointsPerLevelUp(newValue);

            newValue = Integer.parseInt(edtLevelUpFeaturesLevels.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setLevelsForFeatPoints(newValue);

            newValue = Integer.parseInt(edtLevelUpFeaturesPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getClasses().get(index).setFeatPointsPerLevelUp(newValue);

            ((ActCharacteristicEditorFragHolder) getActivity()).saveGameToFile();
        }
    }
}
