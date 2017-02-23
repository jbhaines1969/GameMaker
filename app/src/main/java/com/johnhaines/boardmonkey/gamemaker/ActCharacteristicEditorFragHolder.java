package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActCharacteristicEditorFragHolder extends Activity implements
        OnClickListener, FragListSelector.OnFragmentInteractionListener,
        FragListEdit.OnFragmentInteractionListener,
        FragRaceEditorControlBar.OnFragmentInteractionListener,
        FragClassEditorControlBar.OnFragmentInteractionListener,
        FragInfoTextFragment.OnFragmentInteractionListener,
FragAbilityEditorControlBar.OnFragmentInteractionListener,
        MediaPlayer.OnCompletionListener {

    public static final String LIST_TYPE_KEY = "listType";
    public static final String INDEX_KEY = "index";

    private FrameLayout frmInfoFrame;
    private RelativeLayout backgroundLayout;
    private String infoText;
    private ButtonNoClick btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristic_editor_frag_holder);

        backgroundLayout = (RelativeLayout) findViewById(R.id.CharEditMenuLayout);
        frmInfoFrame = (FrameLayout) findViewById(R.id.frmCharEditorInfo);
        infoText = getInfoText();
        btnInfo = (ButtonNoClick) findViewById(R.id.btnCharEditInformation);
        btnInfo.setOnClickListener(this);
        getInfoButtonImage(btnInfo);
        setBackgroundImage();

        if (savedInstanceState != null) {
            return;
        } else {
            FragListSelector fragLS = new FragListSelector();
            getFragmentManager().beginTransaction().add(R.id.fragment_container_left, fragLS).commit();
        }
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

            if (view == btnInfo) {

                //infoText = getInfoText();
                infoText = getInfoText();

                frmInfoFrame.bringToFront();

                FragInfoTextFragment fragInfo = new FragInfoTextFragment();
                Bundle bundle = new Bundle();
                bundle.putString("text", infoText);
                fragInfo.setArguments(bundle);
                getFragmentManager().beginTransaction().
                        add(R.id.frmCharEditorInfo, fragInfo).commit();
            }
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

    @Override
    public void onBackPressed() {

        // Clear all fragments before popping backstack, as some fragments in right hand container
        // will change after transaction is added to
        if (getFragmentManager().findFragmentById(R.id.frmCharEditorInfo) != null) {
            getFragmentManager().beginTransaction().
                    remove(getFragmentManager().findFragmentById(R.id.frmCharEditorInfo)).commit();
        } else if (getFragmentManager().findFragmentById(R.id.fragment_container_right) != null) {

            getFragmentManager().beginTransaction().remove(getFragmentManager().
                    findFragmentById(R.id.fragment_container_right)).commit();
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (((GameApplication) getApplication()).getGame().getType()) {
            case ("Fantasy"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Sci-Fi"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            default:
                btn.setTextColor(ContextCompat.getColorStateList(this, R.color.button_fantasy_text_primary));
                btn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
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
    public void onListSelectorInteraction(String message, Integer index) {

        // Interface from List Selector screen FragListSelector
        // listType determines which ArrayList the Adapter uses to populate ListView
        // in FragListEdit, and which text to load in the info screen

        switch (message) {
            case "Attributes":
                infoText = getResources().getString(R.string.attribute_list_info);
                break;
            case "Races":
                infoText = getResources().getString(R.string.race_list_info);
                break;
            case "Classes":
                infoText = getResources().getString(R.string.class_list_info);
                break;
            case "Skills":
                infoText = getResources().getString(R.string.skill_list_info);
                break;
            case "Traits":
                infoText = getResources().getString(R.string.trait_list_info);
                break;
            case "Features":
                infoText = getResources().getString(R.string.feature_list_info);
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putString(LIST_TYPE_KEY, message);
        bundle.putInt(INDEX_KEY, index);
        FragListEdit fragLE = new FragListEdit();
        fragLE.setArguments(bundle);
        getFragmentManager().beginTransaction().
                replace(R.id.fragment_container_right, fragLE).commit();
    }

    public String getInfoText() {
        String infoString = "";
        if (getFragmentManager().
                findFragmentById(R.id.fragment_container_left) instanceof
                FragListSelector) {

            if (getFragmentManager().
                    findFragmentById(R.id.fragment_container_right) != null) {
                FragListEdit listFrag = (FragListEdit) getFragmentManager().
                        findFragmentById(R.id.fragment_container_right);
                switch (listFrag.getListType()) {
                    case ("Attributes"):
                        infoString = getResources().getString(R.string.attribute_list_info);
                        break;
                    case ("Races"):
                        infoString = getResources().getString(R.string.race_list_info);
                        break;
                    case ("Classes"):
                        infoString = getResources().getString(R.string.class_list_info);
                        break;
                    case ("Skills"):
                        infoString = getResources().getString(R.string.skill_list_info);
                        break;
                    case ("Traits"):
                        infoString = getResources().getString(R.string.trait_list_info);
                        break;
                    case ("Features"):
                        infoString = getResources().getString(R.string.feature_list_info);
                        break;
                    default:
                        infoString = "I don't think this list exists";
                }
            } else {
                infoString = getResources().getString(R.string.list_selector_info);
            }
        } else if (getFragmentManager().
                findFragmentById(R.id.fragment_container_right) instanceof
                FragAttributeDescription) {
            FragAttributeDescription fragAD = (FragAttributeDescription) getFragmentManager().
                    findFragmentById(R.id.fragment_container_right);
            switch (fragAD.getListType()) {
                case ("Attributes"):
                    infoString = getResources().getString(R.string.attribute_description_info);
                    break;
                case ("Races"):
                    infoString = getResources().getString(R.string.race_description_info);
                    break;
                case ("Classes"):
                    infoString = getResources().getString(R.string.class_description_info);
                    break;
                case ("Skills"):
                    infoString = getResources().getString(R.string.skill_description_info);
                    break;
                case ("Traits"):
                    infoString = getResources().getString(R.string.trait_description_info);
                    break;
                case ("Features"):
                    infoString = getResources().getString(R.string.feature_description_info);
                    break;
                default:
                    infoString = "I don't know what else you could describe here";
            }
        } else {

            Fragment rightFrag = getFragmentManager().findFragmentById(R.id.fragment_container_right);
            Fragment leftFrag = getFragmentManager().findFragmentById(R.id.fragment_container_left);
            if (rightFrag instanceof FragClassEditorControlBar) {
                infoString = getResources().getString(R.string.class_list_info);

            } else if (rightFrag instanceof FragRaceEditorControlBar) {
                infoString = getResources().getString(R.string.race_list_info);

            } else if (rightFrag instanceof FragRaceEditorAttributeMods) {
                infoString = getResources().getString(R.string.race_att_mods_info);

            } else if (rightFrag instanceof FragRaceEditorAttributeRequirements) {
                infoString = getResources().getString(R.string.race_att_req_info);

            } else if (rightFrag instanceof FragRaceEditorMovement) {
                infoString = getResources().getString(R.string.race_movement_info);

            } else if (rightFrag instanceof FragClassEditorLevelUpPoints) {
                infoString = getResources().getString(R.string.class_level_points_info);

            } else if (rightFrag instanceof FragClassEditorStartingPoints) {
                infoString = getResources().getString(R.string.class_start_points_info);

            } else if (rightFrag instanceof FragClassEditorMinimumAttributes) {
                infoString = getResources().getString(R.string.class_att_req_info);

            } else if (rightFrag instanceof FragRaceEditorClassesAllowed) {
                infoString = getResources().getString(R.string.race_allowed_classes_info);

            } else if (rightFrag instanceof FragListEditorKnownSkills) {

                if (leftFrag instanceof FragClassEditorControlBar) {
                    infoString = getResources().getString(R.string.class_skills_info);

                } else if (leftFrag instanceof FragRaceEditorControlBar) {
                    infoString = getResources().getString(R.string.race_skills_info);
                }

            } else if (rightFrag instanceof FragListEditorTraitsPossessed) {

                if (leftFrag instanceof FragClassEditorControlBar) {
                    infoString = getResources().getString(R.string.class_traits_info);

                } else if (leftFrag instanceof FragRaceEditorControlBar) {
                    infoString = getResources().getString(R.string.race_traits_info);
                }
            }
        }

        return infoString;
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

            FragAbilityEditorControlBar fragEditor = new FragAbilityEditorControlBar();
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

        if (listType.equals("Features")) {

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

        if (listType.equals("Traits")) {

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

    @Override
    public void onAbilityEditorButtonClicked(String message, Integer index) {

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX_KEY, index);
        bundle.putString(LIST_TYPE_KEY, "Classes");
        boolean controlsInLeftFrame = getFragmentManager().
                findFragmentById(R.id.fragment_container_left)
                instanceof FragClassEditorControlBar;
        FragAbilityEditorControlBar fragCECB = new FragAbilityEditorControlBar();
        fragCECB.setArguments(bundle);

        switch (message) {
            case ("Description"):
                FragAttributeDescription fragAD = new FragAttributeDescription();
                fragAD.setArguments(bundle);

                if (!(controlsInLeftFrame)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAD).
                            replace(R.id.fragment_container_left, fragCECB).addToBackStack(null).commit();
                } else if (!(getFragmentManager().
                        findFragmentById(R.id.fragment_container_right)
                        instanceof FragAttributeDescription)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAD).commit();
                }

                break;
            case ("Effect"):

                FragAbilityEffectTypeEditor fragAET = new FragAbilityEffectTypeEditor();
                fragAET.setArguments(bundle);

                if (!(controlsInLeftFrame)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAET).
                            replace(R.id.fragment_container_left, fragCECB).
                            addToBackStack(null).commit();
                } else if (!(getFragmentManager().
                        findFragmentById(R.id.fragment_container_right)
                        instanceof FragAbilityEffectTypeEditor)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAET).commit();
                }

                break;
            case ("Classes"):

                FragAbilityPrefClassRaceEditor fragAPC = new FragAbilityPrefClassRaceEditor();
                fragAPC.setArguments(bundle);

                if (!(controlsInLeftFrame)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAPC).
                            replace(R.id.fragment_container_left, fragCECB).
                            addToBackStack(null).commit();
                } else if (!(getFragmentManager().
                        findFragmentById(R.id.fragment_container_right)
                        instanceof FragAbilityPrefClassRaceEditor)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAPC).commit();
                }

                break;
            case ("Races"):

                FragAbilityPrefClassRaceEditor fragAPCR = new FragAbilityPrefClassRaceEditor();
                fragAPCR.setArguments(bundle);

                if (!(controlsInLeftFrame)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAPCR).
                            replace(R.id.fragment_container_left, fragCECB).
                            addToBackStack(null).commit();
                } else if (!(getFragmentManager().
                        findFragmentById(R.id.fragment_container_right)
                        instanceof FragAbilityPrefClassRaceEditor)) {
                    getFragmentManager().beginTransaction().
                            replace(R.id.fragment_container_right, fragAPCR).commit();
                }

                break;
        }

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
