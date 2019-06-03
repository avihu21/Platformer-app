package com.tegi.platformer;

import android.graphics.PointF;

class PlayerUpdateComponent implements UpdateComponent{

    private boolean mIsJumping = false;
    private long mJumpStartTime;

    //how long a jump lasts further subdevided into up and down time later
    private final long MAX_JUMP_TIME = 400;
    private final float GRAVITY = 6;

    public void update(long fps,Transform t,Transform playerTransform){

        //cast to player transform
        PlayerTransform pt = (PlayerTransform) t;
        //where is the player?
        PointF location = t.getLocation();
        //how fast is it going
        float speed = t.getSpeed();

        if (t.headingLeft()){
            location.x -= speed/fps;
        } else if (t.headingRight()){
            location.x += speed/fps;
        }

        //has the player bumbed their head
        if (pt.bumpedHead()){
            mIsJumping = false;
            pt.handlingBumpedHead();
        }

        /*
        check if jump was triggered by the player and if player is NOT ALREADY jumping
        or falling (because we dont want to jump in mid air)  to enable double jump(or more)
        allow the jump when not grounded and count the number of none-grounded jumps and
        disallow jump when the preferred limit is reached
         */
        if (pt.jumpTriggered() && !mIsJumping && pt.isGrounded()){
            SoundEngine.playJump();
            mIsJumping = true;
            pt.handlingJump();
            mJumpStartTime = System.currentTimeMillis();
        }

        //gravity
        if (!mIsJumping){
            //player is not jumping apply GRAVITY
            location.y += GRAVITY/fps;
        } else if (mIsJumping){
            //player is jumping
            pt.setNoGrounded();

            //still in upward phase of the jump
            if (System.currentTimeMillis() < mJumpStartTime + (MAX_JUMP_TIME/1.5)){
                //keep going up
                location.y -= (GRAVITY * 1.8)/fps;
            }

            //in downward phase of the jump
            else if (System.currentTimeMillis() < mJumpStartTime + MAX_JUMP_TIME){
                //come back down
                location.y += (GRAVITY)/fps;
            }

            //has the jump ended
            else if (System.currentTimeMillis() > mJumpStartTime + MAX_JUMP_TIME){
                //time to end the jump
                mIsJumping = false;
            }
        }

        //let the colliders know the new position
        t.updateCollider();
    }





}
