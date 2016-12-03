package com.butcher.test90.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.butcher.test90.R;
import com.butcher.test90.adapter.Commodity_Adapter;
import com.butcher.test90.bean.CommodityDetail;
import com.butcher.test90.bean.GoodsInfo;
import com.butcher.test90.net.RequestData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main_Activity extends Activity {

    /** 日志输出标识 */
    protected final String TAG = this.getClass().getName();

    private Context mContext;
    private ListView mListView;
    private LinearLayout mShowTitle;
    private Commodity_Adapter mAdapter;
    private  List<CommodityDetail> dataList;  // 一个泛型为 CommodityDetail 的 List 引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initView();  // 先走完这个方法中的内容
        initData();  // 布局填充好后，来初始化数据，即，填充数据
        initListener();

    }

    private void initView() {

        mShowTitle = (LinearLayout) findViewById(R.id.invis);  // 展示标题
        mListView = (ListView) findViewById(R.id.lv);

        /*
        inflate() 方法的作用：Inflate a view from an XML resource. 即，用 an XML resource 填充成一个 view
         */
        View head1 = View.inflate(this, R.layout.stick_header, null);  // 向 View 中填充 预留的轮播图布局
        View head2 = View.inflate(this, R.layout.stick_action, null);  // 向 View 中填充 预留的“商品列表”布局
        View head3 = View.inflate(this, R.layout.view_home_sign_mall, null);  // 向 View 中填充 "签到"和“商城”布局

        // 用填充好的 view 给 ListView 添加头 View
        mListView.addHeaderView(head1);//ListView 头部内容,轮播图
        mListView.addHeaderView(head2);//ListView条目中的悬浮部分 添加到头部
        mListView.addHeaderView(head3);// "签到"和“商城”布局

    }

    private void initData() {

        dataList = new ArrayList<>();  // 创建一个泛型为 CommodityDetail 的  ArrayList 集合对象，并赋值给 dataList引用
        mAdapter = new Commodity_Adapter(mContext, dataList);
        mListView.setAdapter(mAdapter);  // 在这个数据初始化模块中，给 ListView 设置适配器

        getGoodsInfoByNet();

    }

    private void initListener() {

        //设置监听item滑动
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem >= 1) {
                    mShowTitle.setVisibility(View.VISIBLE);
                } else {
                    mShowTitle.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * 模拟从网络获取信息
     * 解析json并封装javabean
     */
    private void getGoodsInfoByNet() {

        dataList.clear();
        //从本地获取json模仿从网络请求获得商品信息
        String json = RequestData.requestData("json.txt", this);

        // 如果json为空，可看做为请求失败
        if (TextUtils.isEmpty(json)) {
            Log.v(TAG,"json is null");
            return;
        }
        //打印成功后的json信息
        Log.v(TAG,"json：" + json);

        //Gson解析服务器返回的json信息
        Gson gson = new Gson();
        GoodsInfo mGoodsInfo = gson.fromJson(json , GoodsInfo.class);
        List<CommodityDetail> cartlist = mGoodsInfo.getCartlist();

        dataList.addAll(cartlist);
        mAdapter.setDataList(dataList);

    }

}
