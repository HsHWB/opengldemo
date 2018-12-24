package com.huehn.sense.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Administrator on 2018/12/18.
 * 三角形
 */

public class Triangle {

    private FloatBuffer floatBuffer;
    // 设置每个顶点的坐标数
    public static final int COORDS_PER_VERTEX = 3;

//    // 设置三角形顶点数组
//    public static float triangleCoords[] = { // 默认按逆时针方向顺序绘制
//            0.0f, 0.622008459f, 0.0f,   // 顶
//            -0.5f, -0.311004243f, 0.0f,   // 左底
//            0.5f, -0.311004243f, 0.0f    // 右底
//    };

    // 设置三角形顶点数组
    public static float triangleCoords[] = { // 默认按逆时针方向顺序绘制
            0.0f, 0.5f,  // 顶
            -0.5f, -0.5f,  // 左底
            0.5f, -0.5f // 右底
    };
    // 设置图形的RGB值和透明度
    public float color[] = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};

    private GLUtils glUtils;
    /**
     * 初始化
     * 流程：创建一个顶点的缓冲区空间，然后将其作为一个浮点的缓冲区，
     * 然后将坐标加到这个缓冲区中，然后将读指针指向第一个位置
     */
    public Triangle() {
        // 初始化顶点字节缓冲区，用于存放形状的坐标，
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleCoords.length * 4);// (每个浮点数占用4个字节)
        // 设置使用设备硬件的原生字节序
        byteBuffer.order(ByteOrder.nativeOrder());
        // 将ByteBuffer作为一个浮点缓冲区
        floatBuffer = byteBuffer.asFloatBuffer();
        // 把坐标都添加到FloatBuffer中
        floatBuffer.put(triangleCoords);
        // 设置buffer从第一个坐标开始读
        floatBuffer.position(0);
        glUtils = new GLUtils();
    }

    public void draw(){
        glUtils.draw(COORDS_PER_VERTEX, floatBuffer, color);
    }
}

