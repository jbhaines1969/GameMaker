package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActCharacteristicEditorFragHolder extends Activity implements FragListSelector.OnFragmentInteractionListener,
        FragListEdit.OnFragmentInteractionListener, FragRaceEditorControlBar.OnFragmentInteractionListener,
        FragClassEditorControlBar.OnFragmentInteractionListener, FragInfoTextFragment.OnFragmentInteractionListener {

    public static final String LIST_TYPE_KEY = "listType";
    public static final String INDEX_KEY = "index";

    private FrameLayout frmInfoFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristic_editor_frag_holder);

        frmInfoFrame = (FrameLayout) findViewById(R.id.frmCharEditorInfo);

        if (savedInstanceState != null) {
            return;
        } else {
            FragListSelector fragLS = new FragListSelector();
            getFragmentManager().beginTransaction().add(R.id.fragment_container_left, fragLS).commit();
        }


    }

    @Override
    public void onBackPressed() {

        // Clear all fragments before popping backstack, as some fragments in right hand container
        // will change after transaction is added to stack

        if (getFragmentManager().findFragmentById(R.id.fragment_container_right) != null) {

            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragment_container_right)).commit();
        }
        super.onBackPressed();
    }

    public void charEditInfoButtonClicked(View view) {

        frmInfoFrame.bringToFront();

        FragInfoTextFragment fragInfo = new FragInfoTextFragment();
        String infoText = getInfoText();
        Bundle bundle = new Bundle();
        bundle.putString("text", infoText);
        fragInfo.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.frmCharEditorInfo, fragInfo).addToBackStack(null).commit();

    }

    @Override
    public void onListSelectorInteraction(String message, Integer index) {

        // Interface from List Selector screen FragListSelector
        // listType determines which ArrayList the Adapter uses to populate ListView
        // in FragListEdit

        Bundle bundle = new Bundle();
        bundle.putString(LIST_TYPE_KEY, message);
        bundle.putInt(INDEX_KEY, index);
        FragListEdit fragLE = new FragListEdit();
        fragLE.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragLE).commit();
    }

    @Override
    public void onListEditSelectInList(String listType, Integer index) {


        // Interface from FragListEdit, command when an item is selected in a list
        // listType determined by ArrayList populating ListView, index is position of
        // selected item

        Bundle bundle = new Bundle();
        bundle.putString(LIST_TYPE_KEY, listType);
        bundle.putInt(INDEX_KEY, index);
        FragListEdit fragLE = new FragListEdit();
        fragLE.setArguments(bundle);

        if (listType.equals("Races")) {

            FragRaceEditorControlBar fragEditor = new FragRaceEditorControlBar();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragEditor).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorControlBar)) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).commit();
            } else {
                FragRaceEditorControlBar fragment = (FragRaceEditorControlBar) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }
        }

        if (listType.equals("Attributes")) {

            FragAttributeDescription fragEditor = new FragAttributeDescription();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).addToBackStack(null).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else {

                FragAttributeDescription fragment = (FragAttributeDescription) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }
        }

        if (listType.equals("Skills")) {

            FragAttributeDescription fragEditor = new FragAttributeDescription();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).addToBackStack(null).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else {

                FragAttributeDescription fragment = (FragAttributeDescription) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }

        }

        if (listType.equals("Features")) {

            FragAttributeDescription fragEditor = new FragAttributeDescription();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).addToBackStack(null).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else {

                FragAttributeDescription fragment = (FragAttributeDescription) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }
        }

        if (listType.equals("Traits")) {

            FragAttributeDescription fragEditor = new FragAttributeDescription();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).addToBackStack(null).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else {

                FragAttributeDescription fragment = (FragAttributeDescription) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }
        }

        if (listType.equals("Classes")) {

            FragClassEditorControlBar fragEditor = new FragClassEditorControlBar();
            fragEditor.setArguments(bundle);

            if (!(getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragListEdit)) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).
                        replace(R.id.fragment_container_left, fragLE).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorControlBar)) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_right, fragEditor).commit();
            } else {
                FragRaceEditorControlBar fragment = (FragRaceEditorControlBar) getFragmentManager().findFragmentById(R.id.fragment_container_right);
                fragment.updateIndex(index);
            }

        }
    }

    @Override
    public void onRaceEditorButtonClicked(String message, Integer index) {

        // Interface from FragRaceEditorControlBar
        // message indicates which feature is being edited (which button was pushed)
        // index indicates which Race item is currently being edited

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_KEY, index);
        bundle.putString(LIST_TYPE_KEY, "Races");
        boolean controlsInLeftFrame = getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragRaceEditorControlBar;
        FragRaceEditorControlBar fragRECB = new FragRaceEditorControlBar();
        fragRECB.setArguments(bundle);

        if (message.equals("Description")) {

            FragAttributeDescription fragAD = new FragAttributeDescription();
            fragAD.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragAD).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragAttributeDescription)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragAD).commit();
            }
        }

        if (message.equals("Movement")) {

            FragRaceEditorMovement fragRM = new FragRaceEditorMovement();
            fragRM.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragRM).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorMovement)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragRM).commit();
            }
        }

        if (message.equals("Classes")) {

            FragRaceEditorClassesAllowed fragRCA = new FragRaceEditorClassesAllowed();
            fragRCA.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragRCA).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorClassesAllowed)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragRCA).commit();
            }
        }

        if (message.equals("Features")) {

            FragListEditorFeaturesPossessed fragFP = new FragListEditorFeaturesPossessed();
            fragFP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragFP).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorFeaturesPossessed)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragFP).commit();
            }
        }

        if (message.equals("Skills")) {

            FragListEditorKnownSkills fragKS = new FragListEditorKnownSkills();
            fragKS.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragKS).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorKnownSkills)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragKS).commit();
            }
        }

        if (message.equals("Traits")) {

            FragListEditorTraitsPossessed fragTP = new FragListEditorTraitsPossessed();
            fragTP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragTP).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorTraitsPossessed)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragTP).commit();
            }
        }

        if (message.equals("AttributeMods")) {

            FragRaceEditorAttributeMods fragREAM = new FragRaceEditorAttributeMods();
            fragREAM.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragREAM).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorAttributeMods)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragREAM).commit();
            }
        }

        if (message.equals("Attribute Requirements")) {

            FragRaceEditorAttributeRequirements fragREAR = new FragRaceEditorAttributeRequirements();
            fragREAR.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragREAR).
                        replace(R.id.fragment_container_left, fragRECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragRaceEditorAttributeRequirements)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragREAR).commit();
            }
        }
    }

    @Override
    public void onClassEditorButtonClicked(String message, Integer index) {

        // Interface from FragClassEditorControlBar
        // message indicates which feature is being edited (which button was pushed)
        // index indicates which Class item is being edited

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_KEY, index);
        bundle.putString(LIST_TYPE_KEY, "Classes");
        boolean controlsInLeftFrame = getFragmentManager().findFragmentById(R.id.fragment_container_left) instanceof FragClassEditorControlBar;
        FragClassEditorControlBar fragCECB = new FragClassEditorControlBar();
        fragCECB.setArguments(bundle);

        if (message.equals("Description")) {

            FragAttributeDescription fragAD = new FragAttributeDescription();
            fragAD.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragAD).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragAttributeDescription)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragAD).commit();
            }
        }

        if (message.equals("Starting Points")) {

            FragClassEditorStartingPoints fragSP = new FragClassEditorStartingPoints();
            fragSP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragSP).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragClassEditorStartingPoints)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragSP).commit();
            }
        }

        if (message.equals("Level Points")) {

            FragClassEditorLevelUpPoints fragLP = new FragClassEditorLevelUpPoints();
            fragLP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragLP).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragClassEditorLevelUpPoints)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragLP).commit();
            }
        }

        if (message.equals("Features")) {

            FragListEditorFeaturesPossessed fragFP = new FragListEditorFeaturesPossessed();
            fragFP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragFP).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorFeaturesPossessed)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragFP).commit();
            }
        }

        if (message.equals("Skills")) {

            FragListEditorKnownSkills fragKS = new FragListEditorKnownSkills();
            fragKS.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragKS).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorKnownSkills)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragKS).commit();
            }
        }

        if (message.equals("Traits")) {

            FragListEditorTraitsPossessed fragTP = new FragListEditorTraitsPossessed();
            fragTP.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragTP).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEditorTraitsPossessed)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragTP).commit();
            }
        }

        if (message.equals("Attribute Requirements")) {

            FragClassEditorMinimumAttributes fragMA = new FragClassEditorMinimumAttributes();
            fragMA.setArguments(bundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragMA).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragClassEditorMinimumAttributes)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragMA).commit();
            }
        }

        if (message.equals("Level Names")) {

            FragListEdit fragLE = new FragListEdit();
            Bundle newBundle = new Bundle();
            newBundle.putString(LIST_TYPE_KEY, "Level Names");
            newBundle.putInt(INDEX_KEY, index);
            fragLE.setArguments(newBundle);

            if (!(controlsInLeftFrame)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragLE).
                        replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
            } else if (!(getFragmentManager().findFragmentById(R.id.fragment_container_right) instanceof FragListEdit)) {
                getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container_right, fragLE).commit();
            }

        }
    }

    private String getInfoText() {
        return getResources().getString(R.string.char_edit_info);

    }

    @Override
    public void onDoneButtonClicked() {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmCharEditorInfo)).commit();
    }

    public void saveGameToFile() {

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
                File file = new File(getExternalStoragePublicDirectory(DIRECTORY_DCIM) + "/GameMaker", filename);
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
