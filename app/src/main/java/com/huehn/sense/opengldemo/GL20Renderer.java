package com.huehn.sense.opengldemo;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2018/12/18.
 */

public class GL20Renderer implements GLSurfaceView.Renderer {

    Triangle triangle = null;
    Rectangle rectangle = null;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        triangle = new Triangle();
        rectangle = new Rectangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        System.out.println("huehn draw");
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//        triangle.draw();
        rectangle.draw();
    }
}
