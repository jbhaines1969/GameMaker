package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class ActGameEdit extends Activity implements FragInfoTextFragment.OnFragmentInteractionListener {

    private TextView gameNameLabel;
    private FrameLayout fragFrame;
    private String gameType;
    private Timer sndDelay;
    private int soundID;
    private FrameLayout infoFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);
        gameNameLabel = (TextView) findViewById(R.id.lblGameNameEditMenu);
        gameNameLabel.setText(((GameApplication) getApplication()).getGame().getName());
        gameType = ((GameApplication) getApplication()).getGame().getType();
        playThemeMusic(gameType);

        infoFrame = (FrameLayout) findViewById(R.id.frmGameEditInfo);

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
        }

        new Timer().schedule(new TimerTask() {

            final MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), soundID);

            @Override
            public void run() {
                mPlayer.setVolume(1, 1);
                mPlayer.setLooping(false);
                mPlayer.start();
            }
        }, 10);
    }

    /* Button Actions */

    public void gameEditInfoButtonClicked(View view) {

        infoFrame.bringToFront();

        FragInfoTextFragment fragInfo = new FragInfoTextFragment();
        String infoText = getResources().getString(R.string.game_edit_info);
        Bundle bundle = new Bundle();
        bundle.putString("text", infoText);
        fragInfo.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.frmGameEditInfo, fragInfo).addToBackStack(null).commit();


    }

    public void editNameClicked(View view) {
        Intent intent = new Intent(this, ActGameName.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void editCharacteristicsClicked(View view) {
        Intent intent = new Intent(this, ActCharacteristicEditorFragHolder.class);
        startActivity(intent);
    }

    public void attributeCreationClicked(View view) {
        Intent intent = new Intent(this, ActAttributeCreationFragmentHolder.class);
        startActivity(intent);
    }


    public void healthCreationClicked(View view) {

        Intent intent = new Intent(this, ActHealthCreationFragmentHolder.class);
        startActivity(intent);
    }

    public void advancementButtonClicked(View view) {
        Intent intent = new Intent(this, ActAdvancementMethod.class);
        startActivity(intent);
    }

    @Override
    public void onDoneButtonClicked() {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmGameEditInfo)).commit();
    }
}
