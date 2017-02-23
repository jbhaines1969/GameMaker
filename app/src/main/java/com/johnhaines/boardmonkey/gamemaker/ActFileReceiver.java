package com.johnhaines.boardmonkey.gamemaker;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActFileReceiver extends Activity {

    private static final int REQUEST_WRITE_EXTERNAL_CODE = 1;

    private TextView label;

    private Intent intent;
    private Uri fileUri;
    private String fileName = "";
    private String uriScheme;
    private InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_receiver);

        label = (TextView) findViewById(R.id.lblFileRecieverText);

        intent = getIntent();
        fileUri = intent.getData();
        uriScheme = fileUri.getScheme();

        String writeState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(writeState)) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // TODO request permission to write
            } else {

                if (uriScheme.equals("content")) {

                    try {
                        int nameIndex = -1;
                        inputStream = getContentResolver().openInputStream(fileUri);
                        Cursor cursor = getContentResolver().query(fileUri, new String[]{"_display_name"}, null, null, null);
                        if (!(cursor == null)) {
                            cursor.moveToFirst();
                            nameIndex = cursor.getColumnIndex("_display_name");
                        } else {
                            throw new FileNotFoundException();
                        }

                        if (nameIndex >= 0) {
                            fileName = cursor.getString(nameIndex);
                        }
                        cursor.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Log.d("Working", "File was not found for inputStream from scheme:content");
                    }
                    writeFileExternal(inputStream, fileName);
                    //Prompt for open GameMaker
                }
                if (uriScheme.equals("http") || uriScheme.equals("https")) {
                    //TODO Make inputStream from uri
                    //Write inputStream to file in correct directory (check for filename duplicate)
                }
                if (uriScheme.equals("file")) {
                    //TODO this is local, check to see if it is in the GameMaker directory
                    //If so, prompt to open game
                    //If not, prompt to move to gamemaker directory (check for fileNameDuplicate), then prompt to open (check for fileName duplicate)
                }
            }
        }
    }

    private void writeFileExternal(InputStream fileIn, String nameIn) {
        File newFile = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker" +"/" + nameIn);
        if (newFile.exists()) {
            label.setText("A Game by that name already exists in your Game directory.  How do you wish to proceed?");
        } else {
            try {
                FileOutputStream outFile = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                int n = 0;
                while ((n = fileIn.read(buffer)) != -1) {
                    outFile.write(buffer, 0, n);
                }
                outFile.close();
                fileIn.close();
                label.setText("The file was successully saved to your device, open GameMaker and choose \"Edit Game\" to view and edit it");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



