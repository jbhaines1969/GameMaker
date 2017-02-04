package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ActHealthCreationFragmentHolder extends Activity implements View.OnClickListener, FragInfoTextFragment.OnFragmentInteractionListener {

    private TextView lblGameName;
    private Spinner spnHealthCreationMethod;
    private ArrayAdapter SAdapter;
    private FrameLayout fragFrame;
    private Button btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_creation_fragment_holder);

        fragFrame = (FrameLayout) findViewById(R.id.frmHealthCreationInfo);
        fragFrame.bringToFront();

        btnInfo = (Button) findViewById(R.id.btnHealthCreationInfo);
        btnInfo.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if (view == btnInfo) {

            FragInfoTextFragment fragInfo = new FragInfoTextFragment();
            String infoText = getResources().getString(R.string.health_creation_info);
            Bundle bundle = new Bundle();
            bundle.putString("text", infoText);
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
