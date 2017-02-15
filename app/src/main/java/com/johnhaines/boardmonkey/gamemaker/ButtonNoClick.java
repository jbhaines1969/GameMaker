package com.johnhaines.boardmonkey.gamemaker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by John on 2/14/2017.
 */

public class ButtonNoClick extends Button {

    public ButtonNoClick(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setSoundEffectsEnabled(false);
    }
}
