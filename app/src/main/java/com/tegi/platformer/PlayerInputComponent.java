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
                case MotionEvent.ACTION_UP:
                    if (buttons.get(HUD.LEFT).contains(x,y)||buttons.get(HUD.RIGHT).contains(x,y)){
                        //player has released either left or right
                        mPlayerTransform.stopHorizontal();
                    }
                    break;

                case MotionEvent.ACTION_DOWN:
                    if (buttons.get(HUD.LEFT).contains(x,y)){
                        //player has pressed left
                                mPlayerTransform.headLeft();
                    } else if (buttons.get(HUD.LEFT).contains(x,y)){
                        //player has pressed right
                        mPlayerTransform.headRight();
                    } else if (buttons.get(HUD.JUMP).contains(x,y)){
                        //player has released the jump button
                        mPlayerPlayerTransform.triggerJump();
                    }
                    break;

                case MotionEvent.ACTION_POINTER_UP:
                    if (buttons.get(HUD.LEFT).contains(x,y)||buttons.get(HUD.RIGHT).contains(x,y)){
                        //player has released either up or down
                        mPlayerTransform.stopHorizontal();
                    }
                    break;

                case MotionEvent.ACTION_POINTER_DOWN:
                    if (buttons.get(HUD.LEFT).contains(x,y)){
                        //player has pressed left
                        mPlayerTransform.headLeft();
                    }
                    break;

            }
        }

    }



}
