package com.fuzhucheng.rxjavartrofitrecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;

import java.util.List;

import lester.scolltextview.scolltextview.BaseScollTextView;

public class ScollTextView extends BaseScollTextView<String> {

    public ScollTextView(Context context) {
        super(context);
    }

    public ScollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public String getItemText(List<String> data, int postion) {
        return data.get(postion);
    }
}
