package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ActAttributeCreationFragmentHolder extends Activity {

    private TextView lblGameName;
    private Spinner spnAttCreationMethod;
    private ArrayAdapter SAdapter;
    private FrameLayout fragFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute_creation_fragment_holder);

        fragFrame = (FrameLayout) findViewById(R.id.frmAttCreationInfo);
        fragFrame.bringToFront();

        lblGameName = (TextView) findViewById(R.id.lblAttCreationControlBar);
        lblGameName.setText(((GameApplication) getApplication()).getGame().getName());

        SAdapter = ArrayAdapter.createFromResource(this, R.array.AttCreationMethods, R.layout.spinner_game_type_view);

        spnAttCreationMethod = (Spinner) findViewById(R.id.spnAttCreationMethod);
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
}
