package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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

public class ActGameName extends Activity {

    private FrameLayout fragFrame;

    private Spinner spnGameType;
    private EditText edtGameName;
    private EditText edtGameDescription;
    private ArrayAdapter SAdapter;

    private ClassGame newSaveGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_type_description);

        edtGameName = (EditText) findViewById(R.id.edtGameName);
        edtGameName.setText(((GameApplication) this.getApplication()).getGame().getName());
        edtGameName.clearFocus();

        edtGameDescription = (EditText) findViewById(R.id.edtGameDescription);
        edtGameDescription.setText(((GameApplication) this.getApplication()).getGame().getDescription());
        edtGameDescription.clearFocus();

        SAdapter = ArrayAdapter.createFromResource(this, R.array.GameTypes, R.layout.spinner_game_type_view);

        spnGameType = (Spinner) findViewById(R.id.spnGameType);
        spnGameType.setAdapter(SAdapter);
        spnGameType.setSelection(setSpinnerSelection());


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
        if (newName.equals(R.string.new_game) || (!(newName.length() > 0))) {

            Toast toast = Toast.makeText(ActGameName.this, R.string.choose_name_for_game, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        } else if (nameNotChanged) {
            newSaveGame.setDescription(newDescription);
            newSaveGame.setType(newType);
            saveGame(newName + extension, newSaveGame);
        } else {

            if (newFile.exists()) {
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
    }

    public void saveGame(String fileName, ClassGame game) {

        /* Checks if external storage is available for read and write */
        setAppearanceComponents(game.getType());
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

        Intent intent = new Intent(this, ActGameEdit.class);
        startActivity(intent);
    }

    private void setAppearanceComponents(String gameType) {
        switch (gameType) {
            case ("Fantasy"):
                newSaveGame.setButtonTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                newSaveGame.setPrimaryButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_fantasy_primary));
                newSaveGame.setInfoButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_fantasy_primary));
                newSaveGame.setBackgroundImage(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            case ("Sci-Fi"):
                newSaveGame.setButtonTextColor(ContextCompat.getColorStateList(this, R.color.button_scifi_text_primary));
                newSaveGame.setPrimaryButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_scifi_primary));
                newSaveGame.setInfoButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_scifi_primary));
                newSaveGame.setBackgroundImage(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            case ("Military"):
                newSaveGame.setButtonTextColor(ContextCompat.getColorStateList(this, R.color.button_military_text_primary));
                newSaveGame.setPrimaryButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_military_primary));
                newSaveGame.setInfoButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_military_primary));
                newSaveGame.setBackgroundImage(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            default:
                newSaveGame.setButtonTextColor(ContextCompat.getColorStateList(this, R.color.button_base_text_primary));
                newSaveGame.setPrimaryButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_base_primary));
                newSaveGame.setInfoButtonBackgroundImage(ContextCompat.getDrawable(this, R.drawable.button_base_primary));
                newSaveGame.setBackgroundImage(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
        }
    }
}
