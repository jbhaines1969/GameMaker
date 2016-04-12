package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActGameDescription extends Activity implements View.OnClickListener {

    TextView lblGameName;
    EditText txtGameDescription;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_description);

        txtGameDescription = (EditText) findViewById(R.id.txtGameDescription);
        txtGameDescription.setText(((GameApplication) this.getApplication()).getGame().getDescription());
        lblGameName = (TextView) findViewById(R.id.lblGameName);
        btnSave = (Button) findViewById(R.id.btnSaveGameDescription);
        btnSave.setOnClickListener(this);
        lblGameName.setText(((GameApplication) this.getApplication()).getGame().getName());

    }


    /* Button Action */

    public void onClick(View view) {
        if (view.getId() == R.id.btnSaveGameDescription) {

            ((GameApplication) this.getApplication()).getGame().setDescription(txtGameDescription.getText().toString());

            Intent intent = new Intent(this, ActGameEdit.class);
            startActivity(intent);

        }
    }
}
