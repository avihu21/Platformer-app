package com.tegi.platformer.Levels;

import java.util.ArrayList;

public abstract class Level {
    //to create more levels extend this class

    ArrayList<String>tiles;

    public ArrayList<String> getTiles(){
        return tiles;
    }

}
