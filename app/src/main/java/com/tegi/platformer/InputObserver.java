package com.tegi.platformer;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

interface InputObserver {

    //this allows InputObserver to be called by GameEngine

    //to handle input
    void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> buttons);

}
