package com.tegi.platformer;

import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

class PlayerInputComponent implements InputObserver{ //implements InputObserver so the class can communicate with the GameEngine

    private Transform mPlayerTransform;
    private PlayerTransform mPlayerPlayerTransform;

    PlayerInputComponent(GameEngine ger){
        ger.addObserver(this);
    }

    public void setTransform(Transform transform){
        mPlayerTransform = transform;
        mPlayerPlayerTransform = (PlayerTransform) mPlayerTransform;
    }

    //required method of InputObserver interface called from the onTouchEvent method
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons){
        int i = event.getActionIndex();
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (!gameState.getPaused()){
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                //case statements here
            }
        }

    }



}
