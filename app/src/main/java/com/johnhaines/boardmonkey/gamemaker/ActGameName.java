package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
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

        spnGameType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
        final ClassGame newSaveGame = ((GameApplication) getApplication()).getGame();

        final String newName = edtGameName.getText().toString();
        final String newDescription = edtGameDescription.getText().toString();
        final String newType = spnGameType.getSelectedItem().toString();
        String oldName = ((GameApplication) this.getApplication()).getGame().getName();

        final File currentFile = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker", oldName + extension);
        final File newFile = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker", newName + extension);

        // Check if names are the same

        boolean nameNotChanged = (newName.equals(oldName));

        //  is so, save new information into saveGame
        if (newName.equals("New Game") || (!(newName.length() > 0))) {

            Toast toast = Toast.makeText(ActGameName.this, "Please choose a name for this game. \"New Game\" is not allowed.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        } else if (nameNotChanged) {
            newSaveGame.setDescription(newDescription);
            newSaveGame.setType(newType);
            saveGame(newName + extension, newSaveGame);
        } else {

            if (newFile.exists()) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ActGameName.this);
                alert.setMessage("A ClassGame with this name already exists. Do you want to overwrite it?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                newSaveGame.setName(newName);
                                newSaveGame.setDescription(newDescription);
                                newSaveGame.setType(newType);
                                currentFile.delete();
                                saveGame(newName + extension, newSaveGame);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
