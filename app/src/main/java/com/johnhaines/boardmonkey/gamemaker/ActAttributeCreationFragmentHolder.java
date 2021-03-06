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

public class ActAttributeCreationFragmentHolder extends Activity implements
        View.OnClickListener, FragInfoTextFragment.OnFragmentInteractionListener,
        MediaPlayer.OnCompletionListener {

    private TextView lblGameName;
    private Spinner spnAttCreationMethod;
    private ArrayAdapter SAdapter;
    private FrameLayout frmInfoFrame;
    private ButtonNoClick btnInfo;
    private RelativeLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_creation_fragment_holder);

        frmInfoFrame = (FrameLayout) findViewById(R.id.frmAttCreationInfo);
        frmInfoFrame.bringToFront();

        backgroundLayout = (RelativeLayout) findViewById(R.id.AttCreationLayout);

        btnInfo = (ButtonNoClick) findViewById(R.id.btnAttCreationInfo);
        btnInfo.setOnClickListener(this);
        getInfoButtonImage(btnInfo);


        lblGameName = (TextView) findViewById(R.id.lblAttCreationControlBar);
        lblGameName.setText(((GameApplication) getApplication()).getGame().getName());

        setBackgroundImage();

        SAdapter = ArrayAdapter.createFromResource(this,
                R.array.AttCreationMethods, R.layout.spinner_game_type_view);
        SAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_game_type);
        spnAttCreationMethod = (Spinner) findViewById(R.id.spnAttCreationMethod);
        setSpinnerBackground(spnAttCreationMethod);
        spnAttCreationMethod.setAdapter(SAdapter);
        spnAttCreationMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((GameApplication) getApplication()).getGame().getAttCreation().setCreationType(position + 1);

                Fragment fragAttCreationEditor;

                switch (position) {
                    case 0:
                        fragAttCreationEditor = new FragAttCreationDiceOnly();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container_att_creation, fragAttCreationEditor).commit();

                        break;

                    case 1:
                        fragAttCreationEditor = new FragAttCreationDiceOnly();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container_att_creation, fragAttCreationEditor).commit();

                        break;

                    case 2:
                        fragAttCreationEditor = new FragAttCreationPointAssignment();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_att_creation, fragAttCreationEditor).
                                commit();

                        break;

                    case 3:
                        fragAttCreationEditor = new FragAttCreationBasePoints();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_att_creation, fragAttCreationEditor).
                                commit();

                        break;

                    case 4:
                        fragAttCreationEditor = new FragAttCreationBasePlusDice();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_att_creation, fragAttCreationEditor).
                                commit();

                        break;

                    case 5:
                        fragAttCreationEditor = new FragAttCreationDicePlusPoints();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_att_creation, fragAttCreationEditor).
                                commit();

                        break;

                    case 6:
                        fragAttCreationEditor = new FragAttCreationBasePlusPoints();
                        getFragmentManager().beginTransaction().
                                replace(R.id.fragment_container_att_creation, fragAttCreationEditor).
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

        spnAttCreationMethod.setSelection(getSpinnerPosition());
    }

    private int getSpinnerPosition() {
        int position = ((GameApplication) getApplication()).getGame().getAttCreation().getCreationType() - 1;
        return position;
    }

    public void setSpinnerBackground(Spinner spinner) {
        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                spinner.setBackground(ContextCompat.getDrawable(this,
                        R.drawable.fan_spinner));
                break;
            case ("Sci-Fi"):
                spinner.setBackground(ContextCompat.getDrawable(this,
                        R.drawable.fan_spinner));
                break;
            case ("Military"):
                spinner.setBackground(ContextCompat.getDrawable(this,
                        R.drawable.fan_spinner));
                break;
            case ("Mixed"):
                spinner.setBackground(ContextCompat.getDrawable(this,
                        R.drawable.fan_spinner));
                break;
            default:
                spinner.setBackground(ContextCompat.getDrawable(this,
                        R.drawable.fan_spinner));
        }
    }

    private void setBackgroundImage() {

        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_activity_background_1000_1667));
                break;
            case ("Sci-Fi"):
                backgroundLayout.setBackground(ContextCompat.
                        getDrawable(this, R.drawable.sci_fi_activity_background));
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

    private void getInfoButtonImage(ButtonNoClick infoBtn) {
        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
                break;
            case ("Sci-Fi"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
                break;
            case ("Military"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
                break;
            default:
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
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

            String infoText = getResources().getString(R.string.att_creation_info_base);
            FragInfoTextFragment fragInfo = new FragInfoTextFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text", infoText);
            fragInfo.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.frmAttCreationInfo, fragInfo).addToBackStack(null).commit();
        }
    }

    public void saveCreationMethod() {
        ((GameApplication) getApplication()).getGame().getAttCreation().setCreationType(spnAttCreationMethod.getSelectedItemPosition() + 1);
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
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmAttCreationInfo)).commit();
    }
}
