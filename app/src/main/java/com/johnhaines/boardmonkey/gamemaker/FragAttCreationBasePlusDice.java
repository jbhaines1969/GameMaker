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
public class FragAttCreationBasePlusDice extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private ButtonNoClick btnSaveBasePlusDice;
    private EditText edtBasePointsBasePlusDice;
    private EditText edtDiceSidesBasePlusDice;
    private EditText edtDiceRollsBasePlusDice;

    private String gameType;

    public FragAttCreationBasePlusDice() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        gameType = ((GameApplication) getActivity().getApplication()).getGame().getType();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_att_creation_base_plus_dice, container, false);

        btnSaveBasePlusDice = (ButtonNoClick) rootView.findViewById(R.id.btnSaveBasePlusDice);
        btnSaveBasePlusDice.setOnClickListener(this);
        getPrimaryButtonImage(btnSaveBasePlusDice);

        edtBasePointsBasePlusDice = (EditText) rootView.findViewById(R.id.edtBasePointsBasePlusDice);
        edtBasePointsBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getBaseScore()));
        edtDiceSidesBasePlusDice = (EditText) rootView.findViewById(R.id.edtDiceSidesBasePlusDice);
        edtDiceSidesBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getDiceSides()));
        edtDiceRollsBasePlusDice = (EditText) rootView.findViewById(R.id.edtDiceRollsBasePlusDice);
        edtDiceRollsBasePlusDice.setText(String.valueOf(((GameApplication) getActivity().getApplication()).getGame().getAttCreation().getDiceRolls()));

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

        if (v == btnSaveBasePlusDice) {

            int points;

            points = Integer.parseInt(edtDiceSidesBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceSides(points);

            points = Integer.parseInt(edtDiceRollsBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setDiceRolls(points);

            points = Integer.parseInt(edtBasePointsBasePlusDice.getText().toString());
            ((GameApplication) getActivity().getApplication()).getGame().getAttCreation().setBaseScore(points);

            ((ActAttributeCreationFragmentHolder) getActivity()).saveCreationMethod();

            getActivity().finish();
        }
    }
}
