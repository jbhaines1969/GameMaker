package com.boardmonkey.johnhaines.gamemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActOpenMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_menu);
    }

    public void newGameClicked (View view){
        Intent intent = new Intent(this, ActNewGame.class);
        startActivity(intent);
    }

    public void editGameClicked (View view){
        //do stuff
    }

    public void deleteGameClicked (View view){
        //do stuff
    }

    public void shareGameClicked (View view){
        //do stuff
    }
}
