package com.butcher.test90.net;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RequestData {

    /**
     * 模仿网络请求
     * @param fileName 文件名
     * @param context  上下文
     * @return  String字符串
     */
    public static String requestData(String fileName, Context context) {

        try {

            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
