package com.butcher.test90.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.butcher.test90.R;

/**
 * 欢迎界面
 */
public class Splash_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        Called when the activity is starting.
        This is where most initialization（初始化） should go:
            calling（调用） setContentView(int) to inflate（填充） the activity's UI,
            using findViewById(int) to programmatically interact with widgets in the UI,
            calling managedQuery(android.net.Uri, String[], String, String[], String)
            to retrieve cursors for data being displayed, etc.

        You can call finish() from within this function, in which case onDestroy()
        will be immediately called without any of the rest of（剩余的） the activity
        lifecycle (onStart(), onResume(), onPause(), etc) executing.

        Derived classes（衍生的类，即子类） must call through to the super class's implementation
        of this method. If they do not, an exception（异常） will be thrown.
         */
        super.onCreate(savedInstanceState);
        /*
        Set the activity content from a layout resource.
        从 a layout resource 中给 activity 设置内容（content）。
         */
        setContentView(R.layout.activity_splash);

    }

    /**
     * 按钮点击事件：<br/>点击后，使用 Intent 跳转到 Main_Activity 界面
     *
     * @param view
     */
    public void cartList(View view) {
        Intent intent = new Intent(this, Main_Activity.class);
        startActivity(intent);
    }
}
