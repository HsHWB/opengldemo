package com.huehn.sense.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by huehn on 2018/12/20.
 * 矩阵
 */

public class Rectangle {

    private FloatBuffer floatBuffer;
    public static final int  COORDS_PER_VERTEX = 4;
//        -0.5f, 1f,
//        -0.5f, -0.5f,
//        0.5f, -0.5f,
//        0.5f, 0.5f
    public static float rectangleCoords[] = {
            -0.5f, 0.5f, // x, y, z, r, g, b,每一行存储一个点的信息，位置和颜色
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f
    };

    private GLUtils glUtils;

    // 设置图形的RGB值和透明度
    public float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    public Rectangle() {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(rectangleCoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(rectangleCoords);
        floatBuffer.position(0);
        glUtils = new GLUtils();
    }

    public void draw(){
        glUtils.draw(COORDS_PER_VERTEX, floatBuffer, color);
    }
}
