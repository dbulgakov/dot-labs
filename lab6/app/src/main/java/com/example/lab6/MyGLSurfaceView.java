package com.example.lab6;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(2);
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
    }

    float mPrevX, mPrevY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float dx = x - mPrevX;
                float dy = y = mPrevY;

                mRenderer.setAngle(mRenderer.getAngle() + ((dx + dy) * 180.0f / 320));
                requestRender();
        }

        mPrevX = x;
        mPrevY = y;
        return true;
    }
}
