package com.tegi.platformer;

import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;

class PhysicsEngine {
    void update(long fps,ArrayList<GameObject> objects,GameState gs){
        for (GameObject object:objects){
            object.update(fps,objects.get(LevelManager.PLAYER_INDEX).getTransform());
        }

        detectCollisions(gs,objects);
    }

    private void detectCollisions(GameState gs,ArrayList<GameObject> objects){
        boolean collisionOccurred = false;

        //something collides with some part of the player most frames
        //here are some handy references get a reference to the players position
        //because there will probably a need to update it
        Transform playerTransform = objects.get(LevelManager.PLAYER_INDEX).getTransform();
        PlayerTransform playerPlayerTransform = (PlayerTransform) playerTransform;

        //get the players extra coliders from the cast objects
        ArrayList<RectF> playerColliders = playerPlayerTransform.getColliders();
        PointF playerLocation = playerTransform.getLocation();

        for (GameObject go: objects){
            //just need to check player collisions with everything else
            if (go.checkActive()){
                //object is active so check for collision with player - anywhere at all
                if (RectF.intersects(go.getTransform().getCollider(),playerTransform.getCollider())){
                    //a collision of some kind has occurred 
                }
            }
        }
    }





}
