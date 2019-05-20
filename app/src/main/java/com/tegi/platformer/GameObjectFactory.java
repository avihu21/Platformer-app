package com.tegi.platformer;

import android.content.Context;
import android.graphics.PointF;

import com.tegi.platformer.GOSpec.GameObjectSpec;

class GameObjectFactory {

    private Context mContext;
    private GameEngine mGameEngineReference;
    private int mPixelsPerMeter;

    GameObjectFactory(Context context,GameEngine gameEngine,int pixelsPerMeter){
        mContext = context;
        mGameEngineReference = gameEngine;
        mPixelsPerMeter = pixelsPerMeter;
    }

    GameObject create(GameObjectSpec spec,PointF location){
        GameObject object = new GameObject();

        int mNumComponents = spec.getComponents().length;
        object.setTag(spec.getTag());

        //first give the game object the right kind of transform

        switch (object.getTag()){
            case "Background":
                //code coming soon
                break;
            case "Player":
                //code coming soon
                break;
            default://normal transform
                object.setTransform(new Transform(spec.getSpeed(),spec.getSize().x,spec.getSize().y,location));
                break;
        }

        //loop through and add/initialize all the components
        for (int i = 0;i < mNumComponents;i++){
            switch (spec.getComponents()[i]){
                case "PlayerInputComponent":
                    //code coming soon
                    break;

                case "AnimatedGraphicsComponent":
                    //code coming soon
                    break;

                case "PlayerUpdateComponent":
                    //code coming soon
                    break;

                case "InanimateBlockGraphicsComponent":
                    object.setGraphics(new InanimateBlockGraphicsComponent(),mContext,spec,spec.getSize(),mPixelsPerMeter);
                    break;

                case "InanimateBlockUpdateComponent":
                    object.setMovement(new InanimateBlockUpdateComponent());
                    break;

                case "MovableBlockUpdateComponent":
                    //code coming soon
                    break;

                case "DecorativeBlockUpdateComponent":
                    object.setMovement(new DecorativeBlockUpdateComponent());
                    break;

                case "BackgroundGraphicsComponent":
                    //code coming soon
                    break;

                case "BackgroundUpdateComponent":
                    //code coming soon
                    break;

                default:
                    //error unidentified component
                    break;
            }
        }

        //return the completed GameObject to the LevelManager class
        return object;
    }


}
