package kawasaki.icm.com.tw.kawasaki_ui.custom;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;

/**
 * Created by icm_mobile on 2018/7/3.
 */

public class Tools {
    public static int convertDpToPixel(Context context, int dp){
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return (int)px;
    }

    public static int convertPixelToDp(int px) {
        int dp = (int) (px / Resources.getSystem().getDisplayMetrics().density);
        return dp;
    }

    public static int getScreenHighPixel(MainActivity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        return display.getHeight();
    }
}
