package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ActHealthCreationFragmentHolder extends Activity implements
        View.OnClickListener,
        FragInfoTextFragment.OnFragmentInteractionListener,
        MediaPlayer.OnCompletionListener {

    private TextView lblGameName;
    private Spinner spnHealthCreationMethod;
    private ArrayAdapter SAdapter;
    private FrameLayout fragFrame;
    private ButtonNoClick btnInfo;
    private RelativeLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_creation_fragment_holder);

        fragFrame = (FrameLayout) findViewById(R.id.frmHealthCreationInfo);
        fragFrame.bringToFront();

        backgroundLayout = (RelativeLayout) findViewById(R.id.HealthCreationLayout);
        setBackgroundImage();

        btnInfo = (ButtonNoClick) findViewById(R.id.btnHealthCreationInfo);
        btnInfo.setOnClickListener(this);
        setInfoButtonResources(btnInfo);

        lblGameName = (TextView) findViewById(R.id.lblHealthCreationControlBar);
        lblGameName.setText(((GameApplication) getApplication()).getGame().getName());

        SAdapter = ArrayAdapter.createFromResource(this, R.array.HealthCreationMethods, R.layout.spinner_game_type_view);

        spnHealthCreationMethod = (Spinner) findViewById(R.id.spnHealthCreationMethod);
        spnHealthCreationMethod.setAdapter(SAdapter);

        spnHealthCreationMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((GameApplication) getApplication()).getGame().getHitsType().setType(position + 1);

                Fragment fragHealthCreationEditor;

                switch (position) {
                    case 0:
                        fragHealthCreationEditor = new FragHealthCreationDiceRoll();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_health_creation, fragHealthCreationEditor).
                                commit();
                        break;

                    case 1:
                        fragHealthCreationEditor = new FragHealthCreationAttributeBased();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_health_creation, fragHealthCreationEditor).
                                commit();
                        break;

                    case 2:
                        fragHealthCreationEditor = new FragHealthCreationAccessory();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_health_creation, fragHealthCreationEditor).
                                commit();
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnHealthCreationMethod.setSelection(getSpinnerPosition());
    }

    private void setBackgroundImage() {

        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Sci-Fi"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Military"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Mixed"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            default:
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
        }

    }

    public void setInfoButtonResources(ButtonNoClick infoBtn) {
        switch (((GameApplication) getApplication()).getGame().getType()) {
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

    @Override
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

        if (view == btnInfo) {

            FragInfoTextFragment fragInfo = new FragInfoTextFragment();
            String infoText = getResources().getString(R.string.health_creation_info);
            Bundle bundle = new Bundle();
            bundle.putString("text", infoText);
            fragInfo.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.frmHealthCreationInfo, fragInfo).addToBackStack(null).commit();
        }
    }

    private int getSpinnerPosition() {
        int position = ((GameApplication) getApplication()).getGame().getHitsType().getType() - 1;
        return position;
    }

    public void saveCreationMethod() {
        ((GameApplication) getApplication()).getGame().getAttCreation().setCreationType(spnHealthCreationMethod.getSelectedItemPosition() + 1);
        ClassGame game = (((GameApplication) getApplication()).getGame());
        String filename = (game.getName() + ".gmgt");

        /* Checks if external storage is available for read and write */
        boolean isExternalStorageWritable = false;
        String writeState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(writeState)) {
            isExternalStorageWritable = true;
        }

        if (isExternalStorageWritable) {

            try {
                File file = new File(getExternalFilesDir(null), filename);
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(game);
                os.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDoneButtonClicked() {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmHealthCreationInfo)).commit();
    }
}
