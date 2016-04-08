package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.os.Bundle;

public class ActCharEditorFragHolder extends Activity implements FragListSelector.OnFragmentInteractionListener,
        FragListEdit.OnFragmentInteractionListener {

    public static final String LIST_TYPE_KEY = "LIST_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_editor_frag_holder);

        if (savedInstanceState != null) {
            return;
        } else {
            FragListSelector fragLS = new FragListSelector();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, fragLS).addToBackStack(null).commit();
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
    public void onListEditSelectInList(String message) {

    }
}
