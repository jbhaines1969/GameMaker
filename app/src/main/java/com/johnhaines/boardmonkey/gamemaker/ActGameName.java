package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActGameName extends Activity implements MediaPlayer.OnCompletionListener {

    private FrameLayout fragFrame;

    private Spinner spnGameType;
    private EditText edtGameName;
    private EditText edtGameDescription;
    private ArrayAdapter SAdapter;

    private ButtonNoClick btnSave;

    private ClassGame newSaveGame;

    private MediaPlayer mPlayer;

    private Boolean spinnerItemSelected; //set to true after first selection, to avoid playing on load


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_type_description);

        btnSave = (ButtonNoClick) findViewById(R.id.btnSaveGame);
        btnSave.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_base_primary));
        btnSave.setTextColor(ContextCompat.getColorStateList(this, R.color.button_base_text_primary));

        spinnerItemSelected = false;

        edtGameName = (EditText) findViewById(R.id.edtGameName);
        edtGameName.setText(((GameApplication) this.getApplication()).getGame().getName());
        edtGameName.clearFocus();

        edtGameDescription = (EditText) findViewById(R.id.edtGameDescription);
        edtGameDescription.setText(((GameApplication) this.getApplication()).getGame().getDescription());
        edtGameDescription.clearFocus();

        SAdapter = ArrayAdapter.createFromResource(this, R.array.GameTypes, R.layout.spinner_game_type_view);

        spnGameType = (Spinner) findViewById(R.id.spnGameType);
        SAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item_game_type);

        spnGameType.setAdapter(SAdapter);
        spnGameType.setBackground(ContextCompat.getDrawable(this, R.drawable.fan_spinner));
        spnGameType.setSelection(setSpinnerSelection());
        spnGameType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                playSpinnerSound();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onPause() {
        spinnerItemSelected = false;
        super.onPause();
    }

    public int setSpinnerSelection() {
        int position = 0;
        String selection = (((GameApplication) this.getApplication()).getGame().getType());

        if (selection.equals("Fantasy")) position = 0;
        if (selection.equals("Sci-Fi")) position = 1;
        if (selection.equals("Military")) position = 2;
        if (selection.equals("Mixed")) position = 3;

        return position;
    }

    private void playSpinnerSound() {

        int soundID = 0;
        if (spinnerItemSelected == true) {
            String selection = spnGameType.getSelectedItem().toString();

            switch (selection) {
                case ("Sci-Fi"):
                    soundID = R.raw.syfi_shr;
                    break;
                case ("Fantasy"):
                    soundID = R.raw.fan_shr;
                    break;
                case ("Military"):
                    soundID = R.raw.mil_shr;
                    break;
                case ("Mixed"):
                    soundID = R.raw.fan_shr;
                    break;
            }

            playSound(soundID);


        } else {
            spinnerItemSelected = true;
        }


    }

    public void playSound(int soundID) {
        try {
            mPlayer = MediaPlayer.create(getApplicationContext(), soundID);

            mPlayer.setVolume(1, 1);
            mPlayer.setLooping(false);
            mPlayer.setOnCompletionListener(this);

            mPlayer.start();
        } catch (Exception e) {
            Log.d("Working", e.toString());
        }
    }


    public void onCompletion(MediaPlayer mPlayer) {

        mPlayer.reset();
        mPlayer.release();

    }

    public void saveNewGameClicked(View view) {
        // initiate and save New game with Name, Description and Type and default values


        final String extension = ".gmgt";
        newSaveGame = ((GameApplication) getApplication()).getGame();

        final String newName = edtGameName.getText().toString();
        final String newDescription = edtGameDescription.getText().toString();
        final String newType = spnGameType.getSelectedItem().toString();
        String oldName = ((GameApplication) this.getApplication()).getGame().getName();

        final File currentFile = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker", oldName + extension);
        final File newFile = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker", newName + extension);

        // Check if names are the same

        boolean nameNotChanged = (newName.equals(oldName));

        //  is so, save new information into saveGame
        if (newName.equals(getResources().getString(R.string.new_game)) || (!(newName.length() > 0))) {

            Toast toast = Toast.makeText(ActGameName.this, R.string.choose_name_for_game, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


        } else if (nameNotChanged) {
            newSaveGame.setDescription(newDescription);
            newSaveGame.setType(newType);
            saveGame(newName + extension, newSaveGame);
        } else if (newFile.exists()) {

            AlertDialog.Builder alert = new AlertDialog.Builder(ActGameName.this);
            alert.setMessage(R.string.game_already_exists)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            newSaveGame.setName(newName);
                            newSaveGame.setDescription(newDescription);
                            newSaveGame.setType(newType);
                            currentFile.delete();
                            saveGame(newName + extension, newSaveGame);
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            alert.create();
            alert.show();

        } else {

            newSaveGame.setName(newName);
            newSaveGame.setDescription(newDescription);
            newSaveGame.setType(newType);
            currentFile.delete();
            saveGame(newName + extension, newSaveGame);
        }
    }

    public void saveGame(String fileName, ClassGame game) {

        /* Checks if external storage is available for read and write */
        setButtonSound(game.getType());
        boolean isExternalStorageWritable = false;
        String writeState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(writeState)) {
            isExternalStorageWritable = true;
        }

        if (isExternalStorageWritable) {

            try {

                File path = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM), "GameMaker");

                if (!path.exists()) {
                    path.mkdirs();
                }
                File file = new File(path, fileName);
                Log.d("Working", "Path is: " + path + ",  FileName is: " + fileName);
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(game);
                os.close();
                fos.close();
                Log.d("Working", "This game was saved to :  " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Working", "This game wasn't saved");
            }
        }

        spinnerItemSelected = false;

        Intent intent = new Intent(this, ActGameEdit.class);
        startActivity(intent);
    }

    private void setButtonSound(String gameType) {
        switch (gameType) {
            case ("Fantasy"):
                newSaveGame.setButtonSoundID(R.raw.fan_hit);
                break;
            case ("Sci-Fi"):
                newSaveGame.setButtonSoundID(R.raw.syfi_hit);
                break;
            case ("Military"):
                newSaveGame.setButtonSoundID(R.raw.mil_hit);
                break;
            default:
                newSaveGame.setButtonSoundID(R.raw.fan_hit);
                break;
        }
    }
}
