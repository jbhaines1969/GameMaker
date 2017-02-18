package com.johnhaines.boardmonkey.gamemaker;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActOpenMenu extends Activity implements
        FragFilePicker.OnFragmentInteractionListener,
        MediaPlayer.OnCompletionListener {

    private static final int REQUEST_WRITE_EXTERNAL_CODE = 1;
    private static final String KEY_FILE_PICKER_TYPE = "selectType";
    private static final String KEY_FILE_EXTENSION = "fileType";

    private FrameLayout fragFrame;

    private ButtonNoClick btnNew;
    private ButtonNoClick btnEdit;
    private ButtonNoClick btnDelete;
    private ButtonNoClick btnShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);

        fragFrame = (FrameLayout) findViewById(R.id.info_frame_open_menu);
        fragFrame.bringToFront();

        btnNew = (ButtonNoClick) findViewById(R.id.btnNewGame);
        getPrimaryButtonImage(btnNew);
        btnEdit = (ButtonNoClick) findViewById(R.id.btnEditGame);
        getPrimaryButtonImage(btnEdit);
        btnDelete = (ButtonNoClick) findViewById(R.id.btnDeleteGame);
        getPrimaryButtonImage(btnDelete);
        btnShare = (ButtonNoClick) findViewById(R.id.btnShareGame);
        getPrimaryButtonImage(btnShare);
    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {
        btn.setBackground(ContextCompat.
                getDrawable(this, R.drawable.selector_button_base_primary));
        btn.setTextColor(ContextCompat.
                getColorStateList(this, R.color.button_base_text_primary));
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

    public void newGameClicked(View view) {
        ClassGame newGame = new ClassGame("New Game", "No Type Selected");
        ((GameApplication) this.getApplication()).setGame(newGame);
        String filename = "New Game.gmgt";

        /* Checks if external storage is available for read and write */
        boolean isExternalStorageWritable = false;
        String writeState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(writeState)) {
            isExternalStorageWritable = true;
        }

        if (isExternalStorageWritable) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                /* TODO warn users app is not able to write to external storage
            warn that permission is needed, else saved files will be written to app internal storage, and deleted if app is uninstalled
            inform user that files written to internal storage can be transferred to external storage when available
            using the Share Game button, and choosing "to external storage".
            inform user that internally stored files will have INTERNAL appended to the file name

            after user closes warning, ask for permission.

            until this code is written, the app will just ask for permission.

            */
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_EXTERNAL_CODE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            } else {
                saveToExternalStorage();

                Intent intent = new Intent(this, ActGameName.class);
                startActivity(intent);
            }
        } else {
            //TODO warn that external storage is unavailable. advise of storing internally and deleting on uninstall. advise on sharing to external when available.
            saveToInternalStorage();
        }
    }

    public void editGameClicked(View view) {

        FragFilePicker picker = new FragFilePicker();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_FILE_PICKER_TYPE, "Edit");
        bundle.putString(KEY_FILE_EXTENSION, ".gmgt");
        picker.setArguments(bundle);
        getFragmentManager().beginTransaction().
                add(R.id.info_frame_open_menu, picker).addToBackStack(null).commit();
    }

    public void deleteGameClicked(View view) {

        FragFilePicker picker = new FragFilePicker();
        Bundle bundle = new Bundle();
        bundle.putString("selectType", "Delete");
        bundle.putString("fileType", ".gmgt");
        picker.setArguments(bundle);
        getFragmentManager().beginTransaction().
                add(R.id.info_frame_open_menu, picker).addToBackStack(null).commit();
    }

    public void shareGameClicked(View view) {

        FragFilePicker picker = new FragFilePicker();
        Bundle bundle = new Bundle();
        bundle.putString("selectType", "Share");
        bundle.putString("fileType", ".gmgt");
        picker.setArguments(bundle);
        getFragmentManager().beginTransaction().
                add(R.id.info_frame_open_menu, picker).addToBackStack(null).commit();
    }

    @Override
    public void filePickerFileChosen(File file) {

        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.info_frame_open_menu)).commit();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClassGame editGame = null;
        try {
            editGame = (ClassGame) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ((GameApplication) getApplication()).setGame(editGame);

        Intent intent = new Intent(this, ActGameEdit.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    saveToExternalStorage();

                } else {

                    saveToInternalStorage();

                }

                Intent intent = new Intent(this, ActGameName.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void saveToExternalStorage() {

        ClassGame newGame = new ClassGame("New Game", "No Type Selected");
        ((GameApplication) this.getApplication()).setGame(newGame);
        String filename = "New Game.gmgt";

        try {
            File path = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM), "GameMaker");

            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, filename);

            Log.d("Working", "Path is: " + path);

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(newGame);
            os.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToInternalStorage() {

    }
}
