package com.tegi.platformer;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

class Renderer {
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    //here is our new camera
    private Camera mCamera;

    Renderer(SurfaceView sh, Point screenSize){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();

        //initialize the camera
        mCamera = new Camera(screenSize.x,screenSize.y);
    }

    int getPixelsPerMeter(){
        return mCamera.getPixelsPerMeter();
    }

    void draw(ArrayList<GameObject> objects,GameState gs,HUD hud){
        if (mSurfaceHolder.getSurface().isValid()){
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,0,0,0));

            if (gs.getDrawing()){
                //set the player as the center of the camera
                mCamera.setWorldCenter(objects.get(LevelManager.PLAYER_INDEX).getTransform().getLocation());

                for (GameObject object:objects){
                    if (object.checkActive()){
                        object.draw(mCanvas,mPaint,mCamera);
                    }
                }

            }

            hud.draw(mCanvas,mPaint,gs);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }


}
