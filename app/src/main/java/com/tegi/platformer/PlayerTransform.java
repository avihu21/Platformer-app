package com.tegi.platformer;

import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

class PlayerTransform extends Transform{

    private ArrayList<RectF> mColliders;
    private final float TENTH = .1f;
    private final float HALF =  .5f;
    private final float THIRD = .3f;
    private final float FIFTH = .2f;
    private final float FEET_PROTRUSION = 1.2f;

    private RectF mHeadRectF = new RectF();
    private RectF mRightRectF = new RectF();
    private RectF mFeetRectF = new RectF();
    private RectF mLeftRectF = new RectF();

    private boolean mJumpTriggered = false;
    private boolean mBumpedHeadTriggered = false;

    private boolean mGrounded;

    PlayerTransform(float speed,float objectWidth,float objectHeight,PointF startingLocation){
        super(speed,objectWidth,objectHeight,startingLocation);

        mColliders = new ArrayList<RectF>();
        //load up the colliders ArrayList wih player specific colliders
        mColliders.add(mFeetRectF);
        mColliders.add(mHeadRectF);
        mColliders.add(mRightRectF);
        mColliders.add(mLeftRectF);
    }
    


}
