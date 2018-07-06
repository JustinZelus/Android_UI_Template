package kawasaki.icm.com.tw.kawasaki_ui.custom;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

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
}
