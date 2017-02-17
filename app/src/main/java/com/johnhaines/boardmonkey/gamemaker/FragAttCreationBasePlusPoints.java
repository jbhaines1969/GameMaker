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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragAttCreationBasePlusPoints extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private ButtonNoClick btnSaveBasePlusPoints;
    private EditText edtBasePointsBasePlusPoints;
    private EditText edtPointsBasePlusPoints;
    private String gameType;

    public FragAttCreationBasePlusPoints() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_base_plus_points, container, false);

        btnSaveBasePlusPoints = (ButtonNoClick) rootView.findViewById(R.id.btnSaveBasePlusPoints);
        btnSaveBasePlusPoints.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveBasePlusPoints);
        edtBasePointsBasePlusPoints = (EditText) rootView.findViewById(R.id.edtBasePointsBasePlusPoints);
        edtBasePointsBasePlusPoints.setText(String.valueOf(((GameApplication) getActivity().
                getApplication()).getGame().getAttCreation().getBaseScore()));
        edtPointsBasePlusPoints = (EditText) rootView.findViewById(R.id.edtPointsBasePlusPoints);
        edtPointsBasePlusPoints.setText(String.valueOf(((GameApplication) getActivity().
                getApplication()).getGame().getAttCreation().getPoints()));

        return rootView;
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

        if (view == btnSaveBasePlusPoints) {

            int points;

            points = Integer.parseInt(edtBasePointsBasePlusPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setBaseScore(points);

            points = Integer.parseInt(edtPointsBasePlusPoints.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setPoints(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
