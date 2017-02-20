package com.fuzhucheng.rxjavartrofitrecyclerview.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast = null;

    public static void showToast(Context mContext, String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void showToast(Context mContext, int id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        } else {
            toast.setText(id);
        }
        toast.show();
    }

}
