package com.tegi.platformer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.tegi.platformer.GOSpec.GameObjectSpec;

class AnimatedGraphicsComponent implements GraphicsComponent{

    private String mBitmapName;
    private Animator mAnimator;
    private Rect mSectionToDraw;

    @Override
    public void initialize(Context context,GameObjectSpec spec,PointF objectSize,int pixelsPerMeter){
        //initialize the animation
        mAnimator = new Animator(objectSize.y,objectSize.x,spec.getNumFrames(),pixelsPerMeter);

        //stretch the bitmap by number of frames
        float totalWidth = objectSize.x*spec.getNumFrames();

        mBitmapName = spec.getBitmapName();
        BitmapStore.addBitmap(context,mBitmapName,new PointF(totalWidth,objectSize.y),pixelsPerMeter,true);

        //get the first frame of animation
        mSectionToDraw = mAnimator.getCurrentFrame(System.currentTimeMillis());
    }

    @Override
    //updated to take a reference to a Camera
    public void draw(Canvas canvas,Paint paint,Transform t,Camera cam){
        //get the section of bitmap to draw when an object is in motion
        //OR if it is a object with zero speed(like a fire tile)
        if (t.headingRight() || t.headingLeft() || t.getSpeed() == 0){
            //player is moving so animate/change the frame
            mSectionToDraw = mAnimator.getCurrentFrame(System.currentTimeMillis());
        }

        //where should the bitmap section be drawn?
        Rect screenCoordinates = cam.worldToScreen(t.getLocation().x,t.getLocation().y,t.getSize().x,t.getSize().y);
        if (t.getFacingRight()){//this if - else block check to see where the player is facing and use either regular or reversed bitmap
            canvas.drawBitmap(BitmapStore.getBitmap(mBitmapName),mSectionToDraw,screenCoordinates,paint);
        } else {
            canvas.drawBitmap(BitmapStore.getBitmapReversed(mBitmapName),mSectionToDraw,screenCoordinates,paint);
        }

    }

}
