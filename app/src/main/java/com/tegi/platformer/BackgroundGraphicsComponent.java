package com.tegi.platformer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import com.tegi.platformer.GOSpec.GameObjectSpec;

class BackgroundGraphicsComponent implements GraphicsComponent{

    private String mBitmapName;

    @Override
    public void initialize(Context context,GameObjectSpec spec,PointF objectSize,int pixelsPerMeter){
        mBitmapName = spec.getBitmapName();
        BitmapStore.addBitmap(context,mBitmapName,objectSize,pixelsPerMeter,true);
    }

    @Override
    public void draw(Canvas canvas,Paint paint,Transform t,Camera cam){

        //cast to a background transform
        BackgroundTransform bt = (BackgroundTransform)t;

        Bitmap bitmap = BitmapStore.getBitmap(mBitmapName);
        Bitmap bitmapReversed = BitmapStore.getBitmapReversed(mBitmapName);

        int scaledxClip = (int)(bt.getXClip() * cam.getPixelsPerMeter());

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        PointF position = t.getLocation();

        float floatstartY = ((cam.getyCenter() - ((cam.getCameraWorldCenterY() - position.y) * cam.getPixelsPerMeterY())));

        int startY = (int)floatstartY;
        float floatendY = ((cam.getyCenter() + ((cam.getCameraWorldCenterY() - position.y - t.getSize().y) * cam.getPixelsPerMeterY())));
        int endY = (int) floatendY;

        //position the reversed bitmap
        Rect fromRect2 = new Rect(width - scaledxClip,0,width,height);
        Rect toRect2 = new Rect(0,startY,scaledxClip,endY);

        //draw the two bitmaps
        if (!bt.getReversedFirst()){
            canvas.drawBitmap(bitmap,);
        }

    }



}
