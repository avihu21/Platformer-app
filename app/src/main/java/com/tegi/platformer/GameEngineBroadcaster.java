package com.tegi.platformer;

interface GameEngineBroadcaster {

    //this allows the player and UI Controller components
    //to add themsselfs as listeners of the GameEngine class
    void addObserver(InputObserver o);

}
