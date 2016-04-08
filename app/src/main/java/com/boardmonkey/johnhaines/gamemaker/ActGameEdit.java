package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActGameEdit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edit);
    }

    /* Button Actions */

    public void editCharacteristicsClicked(View view) {
        Intent intent = new Intent(this, ActCharEditorFragHolder.class);
        startActivity(intent);
    }

    public void attributeCreationClicked(View view) {
        // load attribute creation method fragment
    }
}
