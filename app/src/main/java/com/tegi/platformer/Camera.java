package com.tegi.platformer;

import android.graphics.PointF;
import android.graphics.Rect;

class Camera {

    private PointF mCurrentCameraWorldCenter;
    private Rect mConvertedRect;
    private int mPixelsPerMeter;
    private int mScreenCenterX;
    private int mScreenCenterY;

    Camera(int screenXResolution,int screenYResolution){
        //locate the center of the screen
        mScreenCenterX = screenXResolution/2;
        mScreenCenterY = screenYResolution/2;

        //how many meters of world space does the screen width show
        //change this value to zoom in and out
        final int pixelPerMeterToResolutionRatio = 48;
        mPixelsPerMeter = screenXResolution/pixelPerMeterToResolutionRatio;
        mConvertedRect = new Rect();
        mCurrentCameraWorldCenter = new PointF();
    }

    int getyCenter(){
        return mScreenCenterY;
    }

    int getPixelsPerMeterY(){
        return mPixelsPerMeter;
    }

    float getCameraWorldCenterY(){
        return mCurrentCameraWorldCenter.y;
    }

    //set the camera to the player called each frame
    void setWorldCenter(PointF worldCenter){
        mCurrentCameraWorldCenter.x = worldCenter.x;
        mCurrentCameraWorldCenter.y = worldCenter.y;
    }

    int getPixelsPerMeter(){
        return mPixelsPerMeter;
    }

    //return a Rect of the screen coordinates relative to a world location
    Rect worldToScreen(float objectX,float objectY,float objectWidth,float objectHeight){
        int left = (int)(mScreenCenterX - ((mCurrentCameraWorldCenter.x - objectX)*mPixelsPerMeter));
        int top = (int)(mScreenCenterY-((mCurrentCameraWorldCenter.y - objectY)*mPixelsPerMeter));
        int right = (int)(left + (objectWidth * mPixelsPerMeter));
        int bottom = (int)(top + (objectHeight * mPixelsPerMeter));

        mConvertedRect.set(left,top,right,bottom);
        return mConvertedRect;
    }




}
