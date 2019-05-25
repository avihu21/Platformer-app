package com.tegi.platformer;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.concurrent.CopyOnWriteArrayList;

class GameEngine extends SurfaceView implements Runnable,GameEngineBroadcaster,EngineController{

    private Thread mThread = null;
    private long mFPS;

    private GameState mGameStat;
    UIController mUIConroller;

    //this Arraylist can be accessd from either thread
    private CopyOnWriteArrayList<InputObserver> inputObservers = new CopyOnWriteArrayList<>();

    HUD mHUD;
    LevelManager mLevelManager;
    PhysicsEngine mPhysicsEngine;
    Renderer mRenderer;

    public GameEngine(Context context,Point size){
        super(context);
        //prepare the bitmap store and sound engine
        BitmapStore bs = BitmapStore.getInstance(context);
        SoundEngine se = SoundEngine.getInstance(context);

        //initialize all the significant classes
        //that make the engine work
        mHUD = new HUD(context,size);
        mGameStat = new GameState(this,context);
        mUIConroller = new UIController(this,size);
        mPhysicsEngine = new PhysicsEngine();
        mRenderer = new Renderer(this,size);
        mLevelManager = new LevelManager(context,this,mRenderer.getPixelsPerMeter());
    }

    public void startNewLevel(){
        //clear the bitmap store
        BitmapStore.clearStore();
        //clear all the observers and add the UI observer back
        //when we call buildGameObjects the player's observer will be added too
        inputObservers.clear();
        mUIConroller.addObserver(this);
        mLevelManager.setCurrentLevel(mGameStat.getCurrentLevel());
        mLevelManager.buildGameObjects(mGameStat);
    }

    //for the game engine broadcaster interface
    public void addObserver(InputObserver o){
        inputObservers.add(o);
    }

    @Override
    public void run(){
        while (mGameStat.getThreadRunning()){
            long frameStartTime = System.currentTimeMillis();

            if (!mGameStat.getPaused()){
                mPhysicsEngine.update(mFPS,mLevelManager.getGameObjects(),mGameStat);
            }
            mRenderer.draw(mLevelManager.getGameObjects(),mGameStat,mHUD);

            long timeThisFrame = System.currentTimeMillis() - frameStartTime;

            if (timeThisFrame >= 1){
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND/timeThisFrame;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        for (InputObserver o:inputObservers){
            o.handleInput(motionEvent,mGameStat,mHUD.getControls());
        }
        return true;
    }

    public void stopThread(){
        mGameStat.stopEverything();
        mGameStat.stopThread();
        try {
            mThread.join();
        } catch (InterruptedException e){
            Log.e("Exception","stopThread()" + e.getMessage());
        }

    }

    public void startThread(){
        mGameStat.startThread();
        mThread = new Thread(this);
        mThread.start();
    }


}
