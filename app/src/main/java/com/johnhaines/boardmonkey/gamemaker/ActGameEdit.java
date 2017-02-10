package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
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
    private RelativeLayout backgroundFrame;
    private ClassGame game;
    private Button btnName;
    private Button btnCharacteristics;
    private Button btnAttCreation;
    private Button btnHealthCreation;
    private Button btnAdvanceCreation;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);

        game = ((GameApplication) getApplication()).getGame();
        gameNameLabel = (TextView) findViewById(R.id.lblGameNameEditMenu);
        gameNameLabel.setText(((GameApplication) getApplication()).getGame().getName());
        gameType = ((GameApplication) getApplication()).getGame().getType();

        btnName = (Button) findViewById(R.id.btnEditName);
        btnCharacteristics = (Button) findViewById(R.id.btnEditChar);
        btnAttCreation = (Button) findViewById(R.id.btnAttributeCreation);
        btnHealthCreation = (Button) findViewById(R.id.btnHealthCreation);
        btnAdvanceCreation = (Button) findViewById(R.id.btnAdvancementMethod);
        btnInfo = (Button) findViewById(R.id.btnGameEditInfo);

        game.getPrimaryButtonImage(btnName, btnCharacteristics, btnAttCreation, btnHealthCreation,
                btnAdvanceCreation);
        game.getInfoButtonImage(btnInfo);

        playThemeMusic(gameType);

        infoFrame = (FrameLayout) findViewById(R.id.frmGameEditInfo);
        backgroundFrame = (RelativeLayout) findViewById(R.id.GameEditMenuLayout);
        backgroundFrame.setBackground(game.getBackgroundImage());
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
