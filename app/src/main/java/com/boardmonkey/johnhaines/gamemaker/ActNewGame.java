package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;

public class ActNewGame extends Activity {

    private Spinner spnGameType;
    private EditText txtGameName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        txtGameName = (EditText) findViewById(R.id.txtGameName);

        spnGameType = (Spinner) findViewById(R.id.spnGameType);


        spnGameType.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Button activities

    public void saveNewGame (View view){
        // initiate and save New game with Name and Type and default values

        Game currentGame = new Game(txtGameName.getText().toString(), spnGameType.getSelectedItem().toString());

        ((GameApplication) this.getApplication()).setGame(currentGame);

        Intent intent = new Intent(this, ActGameDescription.class);
        startActivity(intent);
    }
}
