package com.tegi.platformer;

import android.graphics.PointF;

class MoveableBlockUpdateComponent implements UpdateComponent{

    @Override
    public void update(long fps,Transform t,Transform playerTransform){
        PointF location = t.getLocation();
        if (t.headingUp()){ //if the object is going up decrease the vertical value of the position
            location.y -= t.getSpeed()/fps;
        } else if (t.headingDown()){ //if the object is going down increase the vertical value of the position
            location.y += t.getSpeed()/fps;
        } else {
            //must be first update of the game so start with going down
            t.headDown();
        }

        //check if the platform needs to change direction
        if (t.headingUp() && location.y <= t.getStartingPosition().y){
            //back at the starting position so start heading down
            t.headDown();
        } else if (t.headingDown() && location.y >= (t.getStartingPosition().y + t.getSize().y * 10)){
            //moved 10 times the height downwards (lowest position) start heading up
            t.headUp();
        }

        //update the colliders with the new position
        t.updateCollider();
    }

}
