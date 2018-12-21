package com.huehn.sense.opengldemo;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){
        glSurfaceView = findViewById(R.id.gl_surface_view);

    }

    private void initData(){
        // 创建一个OpenGL ES 2.0 context,非常重要
        glSurfaceView.setEGLContextClientVersion(2);
        //设置Renderer到GLSurfaceView
        glSurfaceView.setRenderer(new GL20Renderer());
        // 只有在绘制数据改变时才绘制view
        //此设置会阻止绘制GLSurfaceView的帧，直到你调用了requestRender()，这样会非常高效
//        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (glSurfaceView != null){
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView != null){
            glSurfaceView.onResume();
        }
    }
}
