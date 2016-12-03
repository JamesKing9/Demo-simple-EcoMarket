package com.butcher.test90.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.butcher.test90.R;
import com.butcher.test90.activity.CommodityDetail_Activity;
import com.butcher.test90.bean.CommodityDetail;

import java.util.List;

/**
 * commodity，商品的意思；<br/>
 *
 * 该类是填充商品数据的条目适配器；<br/>
 * 直接继承 android 系统的 BaseAdapter <br/>
 *
 * 给 Listview 创建适配器必须：
 * 1. 有构造方法
 *
 */
public class Commodity_Adapter extends BaseAdapter {

    private Context context;
    private List<CommodityDetail> dataList;

    public void setDataList(List<CommodityDetail> dataList) {
        this.dataList = dataList;
        /*
        Notifies the attached observers(附加的观察者) that the underlying data（基本数据） has been changed
        and any View reflecting the data set should refresh itself.
         */
        notifyDataSetChanged();
    }

    public Commodity_Adapter(Context context, List<CommodityDetail> dataList) {

        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {

        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        if (view == null) {   // 如果“购物车条目 view”为 null

            view = View.inflate(context, R.layout.adapter_cart, null);  // 填充购物车条目 view
            holder = new ViewHolder();  // 创建一个ViewHolder对象

            // 给 ViewHolder对象的属性赋值
            holder.ivImageview = (ImageView) view.findViewById(R.id.iv_imageview); // 商品缩略图
            holder.tvCount = (TextView) view.findViewById(R.id.tv_count);  // 顾客添加的商品的数量
            holder.tvDescription = (TextView) view.findViewById(R.id.tv_description); // 商品的描述
            holder.tvMoney = (TextView) view.findViewById(R.id.tv_money);  // 商品的价格
            holder.tvTitle = (TextView) view.findViewById(R.id.tv_title);  // 商品的名称
            holder.tvCountAdd = (TextView) view.findViewById(R.id.tv_count_add); // TextView 绘制了一个带边框的 “+” 的控件
            holder.tvCountReduce = (TextView) view.findViewById(R.id.tv_count_reduce);  // 使用 TextView 绘制了一个带边框的 “-” 的控件

            /*
            setTag() 方法的作用：（tag，在这里是附属物的意思）
                Sets the tag associated with this view.

                A tag can be used to mark（标志） a view in its hierarchy（层次关系） and
                does not have to be unique（唯一的） within the hierarchy.
                Tags can also be used to store（存储） data
                within a view without resorting to（求助于，依赖于） another data structure.
             */
            // 将 holder对象 作为 tag对象 设置给“购物车条目 view”
            view.setTag(holder);
        } else {

            /*
            getTag() 方法的作用是：
                Returns this view's tag.
             */
            holder = (ViewHolder) view.getTag();  // 如果“购物车条目 view” 存在，就从 tag中取出原来的 holder 赋值给现在的 holder，已达到ViewHolder的复用效果
        }

        final CommodityDetail item = dataList.get(i);  // 取出ArrayList集合中指定位置的数据，并赋值给 item

        holder.ivImageview.setBackgroundColor(Color.parseColor(item.getImg_color()));
        holder.tvCount.setText(String.valueOf(item.getQuantity()));
        holder.tvDescription.setText(item.getMsg());  // 设置商品的描述
        holder.tvMoney.setText("¥ " + item.getMoney());  // 设置商品的价格
        holder.tvTitle.setText(item.getProduct_name()); // 设置商品的名称

        holder.tvCountAdd.setOnClickListener(add(holder,item));
        holder.tvCountReduce.setOnClickListener(reduce(holder,item));
        holder.ivImageview.setOnClickListener(actionDetail(item));

        return view;
    }

    /**
     * 点击图片跳转商品详情页
     * 1.将此图片的颜色传递并更改详情页整个布局的颜色
     * 2.将此商品的 product_id 传递并在详情页展示
     */
    @NonNull
    private View.OnClickListener actionDetail(final CommodityDetail item) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(context, CommodityDetail_Activity.class);
                mIntent.putExtra("product_id", String.valueOf(item.product_id));
                mIntent.putExtra("color",item.img_color);
                context.startActivity(mIntent);

            }
        };
    }

    /**
     * 点击加号的操作
     * 1.更改此条目数量
     * 2.更改底部购买总数量
     * 3.更改底部购买商品总价格
     *
     * （注意：@NonNull 用来标注给定的参数或者返回值不能为null）
     */
    @NonNull
    private View.OnClickListener add(final ViewHolder holder, final CommodityDetail item) {

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentCount = Integer.parseInt(holder.tvCount.getText().toString());

                if(isCanAdd(currentCount,item)){

                    item.quantity ++ ;
                    notifyDataSetChanged();

                } else {
                    Toast.makeText(context,"你最多只能购买 "+item.canBuy+" 件",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    /**
     * 点击减号的操作
     * 1.更改此条目数量
     * 2.更改底部购买总数量
     * 3.更改底部购买商品总价格
     */
    @NonNull
    private View.OnClickListener reduce(final ViewHolder holder, final CommodityDetail item) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int currentCount = Integer.parseInt(holder.tvCount.getText().toString());

                if(isCanReduce(currentCount)){

                    item.quantity -- ;
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context,context.getString(R.string.can_not_empty),Toast.LENGTH_SHORT).show();

                }
            }

        };
    }

    /**
     * 是否能减
     * @param currentCount 当前的数量
     * @return  是否能执行减的操作
     */
    private boolean isCanReduce(int currentCount) {

        return currentCount > 1;
    }

    /**
     * 是否能加
     * @param currentCount 当前的数量
     * @param item  当前所操作的item对象
     * @return  是否能执行增加的操作
     */
    private boolean isCanAdd(int currentCount,CommodityDetail item){

        return currentCount < item.canBuy;
    }

    static class ViewHolder {

        TextView tvCountReduce;
        TextView tvCountAdd;
        ImageView ivImageview;
        TextView tvTitle;
        TextView tvMoney;
        TextView tvDescription;
        TextView tvCount;
    }
}
