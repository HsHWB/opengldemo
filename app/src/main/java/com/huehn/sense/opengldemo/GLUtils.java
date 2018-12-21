package com.huehn.sense.opengldemo;

import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.util.IllegalFormatCodePointException;

/**
 * Created by Administrator on 2018/12/18.
 */

public class GLUtils {

    private static final int LENGTH = 4;

    private final String vertexShaderCode =
            "attribute vec4 vPosition;"+
            "void main(){" +
            "gl_Position = vPosition;" +
            "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";


    public int loadShader(int type, String shaderCode){

        // 创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        // 或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译它
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    /**
     * 顶点着色器（Vertex Shader）：用来渲染形状顶点的OpenGL ES代码
     * @return 顶点着色器
     */
    public int getVertexShader(){
        return loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
    }

    /**
     * 片段着色器（Fragment Shader）：使用颜色或纹理渲染形状表面的OpenGL ES代码。
     * @return
     */
    public int getFragmentShader() {
        return loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
    }

    public int getProgram(){
        //获取顶点着色器
        int vertexShader = getVertexShader();
        //获取片段着色器
        int fragmentShader = getFragmentShader();
        // 创建空的OpenGL ES Program
        int program = GLES20.glCreateProgram();
        // 将vertex shader添加到program
        GLES20.glAttachShader(program, vertexShader);
        // 将fragment shader添加到program
        GLES20.glAttachShader(program, fragmentShader);
        // 创建可执行的 OpenGL ES program
        GLES20.glLinkProgram(program);
        // 添加program到OpenGL ES环境中
        GLES20.glUseProgram(program);
        return program;
    }


    /**
     *
     * @param coords_per_vertex 每个顶点的坐标数
     * @param vertexBuffer 浮点缓冲区
     * @param color 颜色数组，数组的四个数分别为图形的RGB值和透明度
     */
    public void draw(int coords_per_vertex, FloatBuffer vertexBuffer, float color[]) {
        //获取程式
        int program = getProgram();

        //得到处理到顶点着色器的vPosition成员
        int vPositionHandler = GLES20.glGetAttribLocation(program, "vPosition");

        // 启用一个指向图形的顶点数组的handle
        GLES20.glEnableVertexAttribArray(vPositionHandler);

        // 准备坐标数据
        GLES20.glVertexAttribPointer(vPositionHandler, coords_per_vertex,
                GLES20.GL_FLOAT, false,
                LENGTH * 1, vertexBuffer);

        // 得到处理到片段着色器的vPosition成员
        int mColorHandle = GLES20.glGetUniformLocation(program, "vColor");
        //清空所有颜色，设置为白色背景，这样子画出来的三角形区域外的就不会是灰色了
        GLES20.glClearColor(255, 255, 255, 0.0f);
        // 设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        // 绘制三角形比较简单，这里采用glDrawArrays方法(默认是逆时针方向)
        if (coords_per_vertex == Triangle.COORDS_PER_VERTEX) {
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, coords_per_vertex);
        }else if (coords_per_vertex == Rectangle.COORDS_PER_VERTEX){
//            GLES20.glDrawArrays(GLES20.);
        }

        // 禁用指向图形的顶点数组
        GLES20.glDisableVertexAttribArray(vPositionHandler);
    }

}
