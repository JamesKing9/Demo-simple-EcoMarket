package com.butcher.test90.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.butcher.test90.R;

/**
 * 商品详情界面的实现：
 * 1 点击商品条目
 * 2 跳转到相应的商品界面
 *
 */
public class CommodityDetail_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        RelativeLayout ll = (RelativeLayout) findViewById(R.id.ll);
        TextView tv = (TextView) findViewById(R.id.tv);

        String color = getIntent().getStringExtra("color");
        String product_id = getIntent().getStringExtra("product_id");

        ll.setBackgroundColor(Color.parseColor(color));
        tv.setText(getString(R.string.product_id) + product_id);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 用户点击界面，则关闭当前 Activity，回到上个 Activity
                finish();
            }
        });
    }
}
