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
        PlayerTransform playersPlayerTransform = (PlayerTransform) playerTransform;

        //get the players extra coliders from the cast objects
        ArrayList<RectF> playerColliders = playersPlayerTransform.getColliders();
        PointF playerLocation = playerTransform.getLocation();

        for (GameObject go: objects){
            //just need to check player collisions with everything else
            if (go.checkActive()){
                //object is active so check for collision with player - anywhere at all
                if (RectF.intersects(go.getTransform().getCollider(),playerTransform.getCollider())){
                    //a collision of some kind has occurred
                    collisionOccurred = true;
                    //get a reference to the current (being tested) object transform and location
                    Transform testedTransform = go.getTransform();
                    PointF testedLocation = testedTransform.getLocation();
                    //dont check the player itself
                    if (objects.indexOf(go) != LevelManager.PLAYER_INDEX){
                        //where was the player hit?
                        for (int i = 0;i < playerColliders.size();i++){
                            if (RectF.intersects(testedTransform.getCollider(),playerColliders.get(i))){
                                //react to the collision based on body part and object type
                                switch (go.getTag() + "with" + "" + i){
                                    //test feet first to avoid the player sinking into a tile
                                    // and unnecessarily triggering right and left as well
                                    case "Movable Platform with 0"://feet
                                        playersPlayerTransform.grounded();
                                        playerLocation.y = (testedTransform.getLocation().y) - (playerTransform.getSize().y);
                                        break;

                                    case "Death with 0"://feet
                                        gs.death();
                                        break;

                                    case "Inert Tile with 0"://feet
                                        playersPlayerTransform.grounded();
                                        playerLocation.y = (testedTransform.getLocation().y) - (playerTransform.getSize().y);
                                        break;

                                    case "Inert Tile with 1"://head
                                        //just update the player to a suitable height but allow any jumps to continue
                                        playerLocation.y = testedLocation.y + testedTransform.getSize().y;
                                        playersPlayerTransform.triggerBumpedHead();
                                        break;

                                    case "Collectible with 2"://right
                                        SoundEngine.playCoinPickup();
                                        //switch off coin
                                        go.setInactive();
                                        //tell the game state a coin has been found
                                        gs.coinCollected();
                                        break;

                                    case "Inert Tile with 2"://right
                                        //stop the player moving right
                                        playerTransform.stopMovingRight();
                                        //move the player to the left tile
                                        playerLocation.x = (testedTransform.getLocation().x) - playerTransform.getSize().x;
                                        break;

                                    case "Collectible with 3"://left
                                        SoundEngine.playCoinPickup();
                                        //switch off coin
                                        go.setInactive();
                                        //tell the GameState a coin has been found
                                        gs.coinCollected();
                                        break;

                                    case "Inert Tile with 3"://left
                                        //stop the player from moving left
                                        playerTransform.stopMovingLeft();
                                        //move the player to the right of the tile
                                        playerLocation.x = testedLocation.x + testedTransform.getSize().x;
                                        break;

                                    //handle the player's feet reaching the objective tile
                                    case "Objective Tile with 0":
                                        SoundEngine.playReachObjective();
                                        gs.objectiveReached();
                                        break;

                                    default:
                                        break;

                                }
                            }


                        }
                    }
                }
            }
        }

        if (!collisionOccurred){
            //no connection with main player collider so not grounded
            playersPlayerTransform.notGrounded();
        }


    }





}
