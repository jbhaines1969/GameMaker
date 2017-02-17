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
public class FragAttCreationPointAssignment extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private ButtonNoClick btnSavePointAssignment;
    private EditText edtPointAssignment;

    private String gameType;

    public FragAttCreationPointAssignment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_point_assignment, container, false);

        edtPointAssignment = (EditText) rootView.findViewById(R.id.edtPointAssignment);
        edtPointAssignment.setText(String.valueOf(((GameApplication) getActivity().getApplication())
                .getGame().getAttCreation().getPoints()));
        edtPointAssignment.setSelectAllOnFocus(true);
        btnSavePointAssignment = (ButtonNoClick) rootView.findViewById(R.id.btnSavePointAssignment);
        btnSavePointAssignment.setOnClickListener(this);
        getPrimaryButtonImage(btnSavePointAssignment);

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

        if (v == btnSavePointAssignment) {

            int points = Integer.parseInt(edtPointAssignment.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setPoints(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
