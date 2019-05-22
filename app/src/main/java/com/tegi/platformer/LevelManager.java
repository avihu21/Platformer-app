package com.tegi.platformer;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;

import com.tegi.platformer.GOSpec.*;
import com.tegi.platformer.Levels.*;

import java.util.ArrayList;

final class LevelManager {

    static int PLAYER_INDEX = 0;
    private ArrayList<GameObject> objects;
    private Level currentLevel;
    private GameObjectFactory factory;

    LevelManager(Context context,GameEngine ge,int pixelsPerMeter){
        objects = new ArrayList<>();
        factory = new GameObjectFactory(context,ge,pixelsPerMeter);
    }

    void setCurrentLevel(String level){
        switch (level){
            case "underground":
                currentLevel = new LevelUnderground();
                break;

            case "mountains":
                currentLevel = new LevelMountains();
                break;

            case "city":
                currentLevel = new LevelCity();
                break;
        }
    }

    void buildGameObjects(GameState gs){
        //backgrounds 1,2,3(city,underground,mountains,...)
        //p = player
        //g = grass tile
        //o = Objective
        //m = Movable platform
        //b = Brick tile
        //c = mine Cart
        //s = Stone pile
        //l = coaL
        //n = coNcrete
        //a = lAmpost
        //r = scoRched tile
        //w = snoW tile
        //t = stalacTite
        //i = stalagmIte
        //d = Dead tree
        //e = snowy treE
        //x = Collectible
        //z = fire
        //y = invisible death_invisible

        gs.resetCoins();
        objects.clear();
        ArrayList<String> levelToLoad = currentLevel.getTiles();

        for (int row = 0;row < levelToLoad.size();row++){
            for (int column = 0;column < levelToLoad.get(row).length();column++){
                PointF coords = new PointF(column,row);

                switch (levelToLoad.get(row).charAt(column)){
                    case '1':
                        //objects.add(factory.create(new BackgroundCitySpec(),coords));
                        break;

                    case '2':
                        //objects.add(factory.create(new BackgroundUndergroundSpec(),coords));
                        break;

                    case '3':
                        //objects.add(factory.create(new BackgroundMountainsSpec(),coords));
                        break;

                    case 'p':
                        //objects.add(factory.create(new PlayerSpec(),coords));
                        //remember the location of the player
                        //PLAYER_INDEX = objects.size()-1;
                        break;

                    case 'g':
                        //objects.add(factory.create(new GrassTileSpec(),coords));
                        break;

                    case 'o':
                        //objects.add(factory.create(new ObjectiveTileSpec(),coords));
                        break;

                    case 'm':
                        //objects.add(factory.create(new MovablePlatformSpec(),coords));
                        break;

                    case 'b':
                        //objects.add(factory.create(new BrickTileSpec(),coords));
                        break;

                    case 'c':
                        //objects.add(factory.create(new CartTileSpec(),coords));
                        break;
                }
            }

        }
    }



}
