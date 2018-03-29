package com.nkbh.xuexue.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by User on 2018/3/29.
 */

public class ToastUtils {
    public static void show(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
