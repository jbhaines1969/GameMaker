package com.boardmonkey.johnhaines.gamemaker;

import android.app.Application;

/**
 * Created by johnhaines on 1/20/16.
 */
public class GameApplication extends Application {

    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {

        return game;
    }
}
