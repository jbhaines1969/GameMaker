package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ActAttributeCreationFragmentHolder extends Activity implements View.OnClickListener, FragInfoTextFragment.OnFragmentInteractionListener {

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

    private void setBackgroundImage() {

        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            case ("Sci-Fi"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            case ("Military"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            case ("Mixed"):
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
                break;
            default:
                backgroundLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.vine_background_1000_1667));
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

    @Override
    public void onClick(View view) {
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
