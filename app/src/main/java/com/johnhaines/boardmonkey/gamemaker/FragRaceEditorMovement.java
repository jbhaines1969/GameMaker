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
 * Use the {@link FragRaceEditorMovement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragRaceEditorMovement extends Fragment implements
        View.OnClickListener,
        MediaPlayer.OnCompletionListener {

    private Integer index;

    private TextView lblWalk;
    private TextView lblRun;
    private TextView lblFly;
    private TextView lblSwim;
    private EditText txtWalk;
    private EditText txtFly;
    private EditText txtSwim;
    private ButtonNoClick btnSave;

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
        btnSave = (ButtonNoClick) rootView.findViewById(R.id.btnSaveMovement);
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
