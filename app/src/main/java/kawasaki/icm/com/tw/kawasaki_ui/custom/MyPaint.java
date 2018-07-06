package kawasaki.icm.com.tw.kawasaki_ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;

import kawasaki.icm.com.tw.kawasaki_ui.R;

/**
 * Created by icm_mobile on 2018/7/6.
 */

public class MyPaint {





    public static Drawable getOriginalColor(Context context) {
        return  context.getResources().getDrawable(R.drawable.rectangle,context.getTheme());
    }

    public static Drawable getHighlightColor(Context context) {
        return  context.getResources().getDrawable(R.drawable.rectangle_fill,context.getTheme());
    }
}
