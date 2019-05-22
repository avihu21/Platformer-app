package com.tegi.platformer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.tegi.platformer.GOSpec.GameObjectSpec;

interface GraphicsComponent {

    //added int mPixelsPerMeter to scale the bitmap to the camera

    void initialize(Context c,GameObjectSpec spec,PointF objectSize,int pixelsPerMeter);

    //updated to take a reference to a camera
    void draw(Canvas canvas, Paint paint, Transform t, Camera cam);

}
