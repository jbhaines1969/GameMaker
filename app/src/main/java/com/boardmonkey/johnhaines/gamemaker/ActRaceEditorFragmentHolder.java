package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.os.Bundle;

public class ActRaceEditorFragmentHolder extends Activity implements FragRaceEditorControlBar.OnFragmentInteractionListener {

    private String listType;
    private Integer index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_editor_fragment_holder);

        if (savedInstanceState != null) {
            listType = savedInstanceState.getString("listType");
            index = savedInstanceState.getInt("index");
            return;
        } else {

            Bundle bundle = getIntent().getExtras();
            listType = bundle.getString("listType");
            index = bundle.getInt("index");
            FragRaceEditorControlBar fragRE = new FragRaceEditorControlBar();
            fragRE.setArguments(bundle);
            getFragmentManager().beginTransaction().add(R.id.race_fragment_container, fragRE).commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString("listType", listType);
        savedInstanceState.putInt("index", index);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRaceEditorButtonClicked(String message, Integer index) {

        Bundle bundle = new Bundle();
        bundle.putInt("index", index);

        if (message.equals("Description")) {
            bundle.putString("listType", listType);
            FragCharacteristicDescription fragCD = new FragCharacteristicDescription();
            fragCD.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.race_fragment_container,fragCD).addToBackStack(null).commit();
        }

        if (message.equals("Movement")) {
            FragRaceMovementEditor fragME = new FragRaceMovementEditor();
            fragME.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.race_fragment_container, fragME).addToBackStack(null).commit();
        }
    }
}
