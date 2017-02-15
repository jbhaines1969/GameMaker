package com.johnhaines.boardmonkey.gamemaker;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static android.os.Environment.DIRECTORY_DCIM;
import static android.os.Environment.getExternalStoragePublicDirectory;

public class ActAdvancementMethod extends Activity implements View.OnClickListener, FragInfoTextFragment.OnFragmentInteractionListener {

    private static final String ATT_POINT_COST = "Att Point Cost";
    private static final String ATT_NON_PREF_PENALTY = "Att Non Pref Penalty";
    private static final String ATT_COST_MULTIPLIER = "Att Cost Multiplier";
    private static final String MAX_ATT_POINTS = "Max Att Points";
    private static final String SKILL_POINT_COST = "Skill Point Cost";
    private static final String SKILL_NON_PREF_PENALTY = "Skill Non Pref Penalty";
    private static final String SKILL_COST_MULTIPLIER = "Skill Cost Multiplier";
    private static final String MAX_SKILL_POINTS = "Max Skill Points";
    private static final String TRAIT_POINT_COST = "Trait Point Cost";
    private static final String TRAIT_NON_PREF_PENALTY = "Trait Non Pref Penalty";
    private static final String TRAIT_COST_MULTIPLIER = "Trait Cost Multiplier";
    private static final String MAX_TRAIT_POINTS = "Max Trait Points";
    private static final String FEAT_POINT_COST = "Feat Point Cost";
    private static final String FEAT_NON_PREF_PENALTY = "Feat Non Pref Penalty";
    private static final String FEAT_COST_MULTIPLIER = "Feat Cost Multiplier";
    private static final String MAX_FEAT_POINTS = "Max Feat Points";

    private FrameLayout frmInfoFrame;
    private RelativeLayout backgroundLayout;
    private Spinner spnAdvancementMethod;
    private SpinnerAdapter SAdapter;
    private TextView lblGameName;
    private EditText edtAttPointCost;
    private EditText edtAttNonPrefPenalty;
    private EditText edtAttCostMultiplier;
    private EditText edtMaxAttPoints;
    private EditText edtSkillPointCost;
    private EditText edtSkillNonPrefPenalty;
    private EditText edtSkillCostMultiplier;
    private EditText edtMaxSkillPoints;
    private EditText edtTraitPointCost;
    private EditText edtTraitNonPrefPenalty;
    private EditText edtTraitCostMultiplier;
    private EditText edtMaxTraitPoints;
    private EditText edtFeatPointCost;
    private EditText edtFeatNonPrefPenalty;
    private EditText edtFeatCostMultiplier;
    private EditText edtMaxFeatPoints;
    private ButtonNoClick btnSave;
    private ButtonNoClick btnInfo;
    private ClassGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = ((GameApplication) getApplication()).getGame();

        setContentView(R.layout.activity_advancement_method);

        frmInfoFrame = (FrameLayout) findViewById(R.id.frmAdvancementMethodInfo);
        frmInfoFrame.bringToFront();
        backgroundLayout = (RelativeLayout) findViewById(R.id.AdvancementMethodLayout);
        lblGameName = (TextView) findViewById(R.id.lblGameNameAdvancementMethod);
        lblGameName.setText(((GameApplication) getApplication()).getGame().getName());
        edtAttPointCost = (EditText) findViewById(R.id.edtAttPointCost);
        edtAttPointCost.setText(setCurrentValues(ATT_POINT_COST));
        edtAttNonPrefPenalty = (EditText) findViewById(R.id.edtAttNonPrefPenalty);
        edtAttNonPrefPenalty.setText(setCurrentValues(ATT_NON_PREF_PENALTY));
        edtAttCostMultiplier = (EditText) findViewById(R.id.edtAttCostMultiplier);
        edtAttCostMultiplier.setText(setCurrentValues(ATT_COST_MULTIPLIER));
        edtMaxAttPoints = (EditText) findViewById(R.id.edtMaxAttPoints);
        edtMaxAttPoints.setText(setCurrentValues(MAX_ATT_POINTS));
        edtSkillPointCost = (EditText) findViewById(R.id.edtSkillPointCost);
        edtSkillPointCost.setText(setCurrentValues(SKILL_POINT_COST));
        edtSkillNonPrefPenalty = (EditText) findViewById(R.id.edtSkillNonPrefPenalty);
        edtSkillNonPrefPenalty.setText(setCurrentValues(SKILL_NON_PREF_PENALTY));
        edtSkillCostMultiplier = (EditText) findViewById(R.id.edtSkillCostMultiplier);
        edtSkillCostMultiplier.setText(setCurrentValues(SKILL_COST_MULTIPLIER));
        edtMaxSkillPoints = (EditText) findViewById(R.id.edtMaxSkillPoints);
        edtMaxSkillPoints.setText(setCurrentValues(MAX_SKILL_POINTS));
        edtTraitPointCost = (EditText) findViewById(R.id.edtTraitPointCost);
        edtTraitPointCost.setText(setCurrentValues(TRAIT_POINT_COST));
        edtTraitNonPrefPenalty = (EditText) findViewById(R.id.edtTraitNonPrefPenalty);
        edtTraitNonPrefPenalty.setText(setCurrentValues(TRAIT_NON_PREF_PENALTY));
        edtTraitCostMultiplier = (EditText) findViewById(R.id.edtTraitCostMultiplier);
        edtTraitCostMultiplier.setText(setCurrentValues(TRAIT_COST_MULTIPLIER));
        edtMaxTraitPoints = (EditText) findViewById(R.id.edtMaxTraitPoints);
        edtMaxTraitPoints.setText(setCurrentValues(MAX_TRAIT_POINTS));
        edtFeatPointCost = (EditText) findViewById(R.id.edtFeatPointCost);
        edtFeatPointCost.setText(setCurrentValues(FEAT_POINT_COST));
        edtFeatNonPrefPenalty = (EditText) findViewById(R.id.edtFeatNonPrefPenalty);
        edtFeatNonPrefPenalty.setText(setCurrentValues(FEAT_NON_PREF_PENALTY));
        edtFeatCostMultiplier = (EditText) findViewById(R.id.edtFeatCostMultiplier);
        edtFeatCostMultiplier.setText(setCurrentValues(FEAT_COST_MULTIPLIER));
        edtMaxFeatPoints = (EditText) findViewById(R.id.edtMaxFeatPoints);
        edtMaxFeatPoints.setText(setCurrentValues(MAX_FEAT_POINTS));

        btnSave = (ButtonNoClick) findViewById(R.id.btnSaveAdvancementMethod);
        btnSave.setOnClickListener(this);
        getPrimaryButtonImage(btnSave);
        btnInfo = (ButtonNoClick) findViewById(R.id.btnAdvancementMethodInfo);
        btnInfo.setOnClickListener(this);
        getInfoButtonImage(btnInfo);

        setBackgroundImage();

        SAdapter = ArrayAdapter.createFromResource(this, R.array.AdvancementMethods, R.layout.spinner_game_type_view);

        spnAdvancementMethod = (Spinner) findViewById(R.id.spnAdvancementMethod);
        spnAdvancementMethod.setAdapter(SAdapter);

        spnAdvancementMethod.setSelection(((GameApplication) getApplication()).getGame().getAdvancementMethod().getType() - 1);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {

        if (view == btnSave) {
            saveValues();
        }

        if (view == btnInfo) {
            loadInfoFragment();
        }

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

    public void getPrimaryButtonImage(ButtonNoClick btn) {

        switch (game.getType()) {
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

    public void getInfoButtonImage(ButtonNoClick infoBtn) {

        switch (game.getType()) {
            case ("Fantasy"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fan_info));
                break;
            case ("Sci-Fi"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            case ("Military"):
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;
            default:
                infoBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.selector_button_fantasy_primary));
                break;

        }
    }

    private String setCurrentValues(String s) {

        String value = "0";

        ClassAdvancementMethod method = ((GameApplication) getApplication()).getGame().getAdvancementMethod();

        switch (s) {

            case ATT_POINT_COST:

                return String.valueOf(method.getAttPointCost());

            case ATT_NON_PREF_PENALTY:

                return String.valueOf(method.getAttNonPrefPenalty());

            case ATT_COST_MULTIPLIER:

                return String.valueOf(method.getAttCostMultiplier());

            case MAX_ATT_POINTS:

                return String.valueOf(method.getMaxAttPoints());

            case SKILL_POINT_COST:

                return String.valueOf(method.getSkillPointCost());

            case SKILL_NON_PREF_PENALTY:

                return String.valueOf(method.getSkillNonPrefPenalty());

            case SKILL_COST_MULTIPLIER:

                return String.valueOf(method.getSkillCostMultiplier());

            case MAX_SKILL_POINTS:

                return String.valueOf(method.getMaxSkillPoints());

            case TRAIT_POINT_COST:

                return String.valueOf(method.getTraitPointCost());

            case TRAIT_NON_PREF_PENALTY:

                return String.valueOf(method.getTraitNonPrefPenalty());

            case TRAIT_COST_MULTIPLIER:

                return String.valueOf(method.getTraitCostMultiplier());

            case MAX_TRAIT_POINTS:

                return String.valueOf(method.getMaxTraitPoints());

            case FEAT_POINT_COST:

                return String.valueOf(method.getFeatPointCost());

            case FEAT_NON_PREF_PENALTY:

                return String.valueOf(method.getFeatNonPreferredPenalty());

            case FEAT_COST_MULTIPLIER:

                return String.valueOf(method.getFeatCostMultiplier());

            case MAX_FEAT_POINTS:

                return String.valueOf(method.getMaxFeatPoints());

            default:
                return s;
        }
    }

    private void saveValues() {

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setType(spnAdvancementMethod.getSelectedItemPosition());

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setAttPointCost(checkForInteger(edtAttPointCost));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setAttNonPrefPenalty(checkForInteger(edtAttNonPrefPenalty));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setAttCostMultiplier(checkForFloat(edtAttCostMultiplier));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setMaxAttPoints(checkForInteger(edtMaxAttPoints));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setSkillPointCost(checkForInteger(edtSkillPointCost));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setSkillNonPrefPenalty(checkForInteger(edtSkillNonPrefPenalty));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setSkillCostMultiplier(checkForFloat(edtSkillCostMultiplier));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setMaxSkillPoints(checkForInteger(edtMaxSkillPoints));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setTraitPointCost(checkForInteger(edtTraitPointCost));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setTraitNonPrefPenalty(checkForInteger(edtTraitNonPrefPenalty));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setTraitCostMultiplier(checkForFloat(edtTraitCostMultiplier));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setMaxTraitPoints(checkForInteger(edtMaxTraitPoints));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setFeatPointCost(checkForInteger(edtFeatPointCost));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setFeatNonPreferredPenalty(checkForInteger(edtFeatNonPrefPenalty));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setFeatCostMultiplier(checkForFloat(edtFeatCostMultiplier));

        ((GameApplication) getApplication()).getGame().getAdvancementMethod().
                setMaxFeatPoints(checkForInteger(edtMaxFeatPoints));

        saveGameToFile();

        finish();

    }

    private Integer checkForInteger(EditText view) {
        int returnInt = 0;

        try {
            returnInt = Integer.valueOf(view.getText().toString());
        } catch (NumberFormatException e) {
            // warn user about needing a number
        }

        return returnInt;
    }

    private Float checkForFloat(EditText view) {
        float returnFloat = 0;

        try {
            returnFloat = Float.valueOf(view.getText().toString());
        } catch (NumberFormatException e) {
            // warn user about needing a number
        }

        return returnFloat;
    }

    private void loadInfoFragment() {

        String infoText = getResources().getString(R.string.advancement_method_info);

        FragInfoTextFragment fragInfo = new FragInfoTextFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", infoText);
        fragInfo.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.frmAdvancementMethodInfo, fragInfo, "info").commit();

    }

    @Override
    public void onDoneButtonClicked() {
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frmAdvancementMethodInfo)).commit();
    }

    private void saveGameToFile() {

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
