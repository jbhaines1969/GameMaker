package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActListEditorFragHolder extends Activity implements FragListSelector.OnFragmentInteractionListener,
        FragListEdit.OnFragmentInteractionListener {

    public static final String LIST_TYPE_KEY = "LIST_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_editor_frag_holder);

        if (savedInstanceState != null) {
            return;
        } else {
            FragListSelector fragLS = new FragListSelector();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragLS).commit();
        }
    }

    @Override
    public void onListSelectorInteraction(String message) {

        Bundle bundle = new Bundle();
        bundle.putString("listType", message);

        FragListEdit fragLE = new FragListEdit();
        fragLE.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragLE).addToBackStack(null).commit();
    }

    @Override
    public void onListEditSelectInList(String listType, Integer index) {



        if (listType == "Races") {

            Intent intent = new Intent(this, ActRaceEditorFragmentHolder.class);
            intent.putExtra("listType", listType);
            intent.putExtra("index", index);
            startActivity(intent);

            //FragRaceEditorControlBar fragRE = new FragRaceEditorControlBar();
            //fragRE.setArguments(bundle);
            //getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragRE).addToBackStack(null).commit();
        }
    }
}
