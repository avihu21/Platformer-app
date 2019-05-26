package com.tegi.platformer;

import android.graphics.Rect;

class Animator {

    private Rect mSourceRect; //hold the 4 corners of the frame to be animated
    private int mFrameCount; //hold the number of frames contained in the sprite sheet and passed from the GOSpec specification class
    private int mCurrentFrame;
    private long mFrameTicker;
    private int mFramePeriod;
    private int mFrameWidth; //hold the pixels width of the animation frame

    Animator(float frameHeight, float frameWidth,int frameCount,int pixelsPerMeter){

        final int ANIM_FPS = 10;
        this.mCurrentFrame = 0;
        this.mFrameCount = frameCount;
        this.mFrameWidth = (int)frameWidth * pixelsPerMeter;
        frameHeight = frameHeight * pixelsPerMeter;
        mSourceRect = new Rect(0,0,this.mFrameWidth,(int)frameHeight);
        mFramePeriod = 1000/ANIM_FPS;
        mFrameTicker = 0L;

    }

    Rect getCurrentFrame(long time){
        if (time > mFrameTicker + mFramePeriod){
            mFrameTicker = time;
            mCurrentFrame++;
            if (mCurrentFrame >= mFrameCount){
                mCurrentFrame = 0;
            }

        }

        //update the left and right values of the source of the next frame on the sprite sheet
        this.mSourceRect.left = mCurrentFrame * mFrameWidth;
        this.mSourceRect.right = this.mSourceRect.left + mFrameWidth;

        return mSourceRect;
    }


}
