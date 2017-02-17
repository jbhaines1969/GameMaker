package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;

public class ActGameEdit extends Activity implements View.OnClickListener, FragInfoTextFragment.OnFragmentInteractionListener,
        MediaPlayer.OnCompletionListener {

    private TextView gameNameLabel;
    private FrameLayout fragFrame;
    private String gameType;
    private Timer sndDelay;
    private int soundID;
    private FrameLayout infoFrame;
    private RelativeLayout backgroundFrame;
    private ClassGame game;
    private ButtonNoClick btnName;
    private ButtonNoClick btnCharacteristics;
    private ButtonNoClick btnAttCreation;
    private ButtonNoClick btnHealthCreation;
    private ButtonNoClick btnAdvanceCreation;
    private ButtonNoClick btnInfo;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        game = ((GameApplication) getApplication()).getGame();
        gameNameLabel = (TextView) findViewById(R.id.lblGameNameEditMenu);
        gameNameLabel.setText(((GameApplication) getApplication()).getGame().getName());
        gameType = ((GameApplication) getApplication()).getGame().getType();

        btnName = (ButtonNoClick) findViewById(R.id.btnEditName);
        getPrimaryButtonImage(btnName);
        btnCharacteristics = (ButtonNoClick) findViewById(R.id.btnEditChar);
        getPrimaryButtonImage(btnCharacteristics);
        btnAttCreation = (ButtonNoClick) findViewById(R.id.btnAttributeCreation);
        getPrimaryButtonImage(btnAttCreation);
        btnHealthCreation = (ButtonNoClick) findViewById(R.id.btnHealthCreation);
        getPrimaryButtonImage(btnHealthCreation);
        btnAdvanceCreation = (ButtonNoClick) findViewById(R.id.btnAdvancementMethod);
        getPrimaryButtonImage(btnAdvanceCreation);
        btnInfo = (ButtonNoClick) findViewById(R.id.btnGameEditInfo);
        getInfoButtonImage(btnInfo);

        playThemeMusic(gameType);

        infoFrame = (FrameLayout) findViewById(R.id.frmGameEditInfo);
        backgroundFrame = (RelativeLayout) findViewById(R.id.GameEditMenuLayout);
        setBackgroundImage();
    }

    private void setBackgroundImage() {

        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                backgroundFrame.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Sci-Fi"):
                backgroundFrame.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Military"):
                backgroundFrame.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Mixed"):
                backgroundFrame.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            default:
                backgroundFrame.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
        }

    }

    private void playThemeMusic(String gameType) {

        soundID = 0;
        switch (gameType) {
            case ("Sci-Fi"):
                soundID = R.raw.syfi_cue;
                break;
            case ("Fantasy"):
                soundID = R.raw.fan_cue;
                break;
            case ("Military"):
                soundID = R.raw.mil_cue;
                break;
            case ("Mixed"):
                soundID = R.raw.fan_cue;
                break;
        }

        playSound(soundID);

    }

    public void playSound(int currenSoundID) {

        MediaPlayer mPlayer = MediaPlayer.create(this, currenSoundID);

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


    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (game.getType()) {
            case ("Fantasy"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Sci-Fi"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;

        }
    }

    public void getInfoButtonImage(ButtonNoClick infoBtn) {

        switch (game.getType()) {
            case ("Fantasy"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
                break;
            case ("Sci-Fi"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            default:
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
        }
    }

    public void onClick(View view) {
        if (view instanceof ButtonNoClick) {
            String gameType = ((GameApplication) getApplication()).getGame().getType();

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
    }

    /* Button Actions */

    public void gameEditInfoButtonClicked(View view) {

        onClick(view);


        infoFrame.bringToFront();

        FragInfoTextFragment fragInfo = new FragInfoTextFragment();
        String infoText = getResources().getString(R.string.game_edit_info);
        Bundle bundle = new Bundle();
        bundle.putString("text", infoText);
        fragInfo.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.frmGameEditInfo, fragInfo).addToBackStack(null).commit();


    }

    public void editNameClicked(View view) {

        onClick(view);

        Intent intent = new Intent(this, ActGameName.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void editCharacteristicsClicked(View view) {

        onClick(view);

        Intent intent = new Intent(this, ActCharacteristicEditorFragHolder.class);
        startActivity(intent);
    }

    public void attributeCreationClicked(View view) {

        onClick(view);
        Intent intent = new Intent(this, ActAttributeCreationFragmentHolder.class);
        startActivity(intent);
    }


    public void healthCreationClicked(View view) {

        onClick(view);

        Intent intent = new Intent(this, ActHealthCreationFragmentHolder.class);
        startActivity(intent);
    }

    public void advancementButtonClicked(View view) {

        onClick(view);
        Intent intent = new Intent(this, ActAdvancementMethod.class);
        startActivity(intent);
    }

    @Override
    public void onDoneButtonClicked() {
        onClick(btnInfo);
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmGameEditInfo)).commit();
    }
}
