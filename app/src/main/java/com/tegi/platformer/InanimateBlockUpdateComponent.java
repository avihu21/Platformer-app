package com.tegi.platformer;

class InanimateBlockUpdateComponent implements UpdateComponent{

    private boolean mColliderNoSet = true;

    @Override
    public void update(long fps,Transform t,Transform playerTransform){
        //an alternative would be to update
        //the collider just once when it spawns
        //but this would require spawn components
        //more code but a bit faster
        if (mColliderNoSet){
            //only need to set the collider
            //once because it will never move
            t.updateCollider();
            mColliderNoSet = false;
        }
    }



}
