package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ActGameEdit extends Activity {

    private TextView gameNameLabel;
    private FrameLayout fragFrame;
    private String gameType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);
        gameNameLabel = (TextView) findViewById(R.id.lblGameNameEditMenu);
        gameNameLabel.setText(((GameApplication) getApplication()).getGame().getName());
        gameType = ((GameApplication) getApplication()).getGame().getType();
        playThemeMusic(gameType);
    }

    private void playThemeMusic(String gameType) {

        int soundID = 0;
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
        MediaPlayer mPlayer = MediaPlayer.create(this, soundID);
        mPlayer.setVolume(1, 1);
        mPlayer.setLooping(false);
        mPlayer.start();
    }

    /* Button Actions */

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
}
