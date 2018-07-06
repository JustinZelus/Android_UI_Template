package kawasaki.icm.com.tw.kawasaki_ui.model;

import android.graphics.drawable.Drawable;

/**
 * Created by icm_mobile on 2018/6/26.
 */

public class Menu {
    String mTitle;
    Drawable mBackground;


    public Menu(String mTitle, Drawable mBackground) {
        this.mTitle = mTitle;
        this.mBackground = mBackground;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Drawable getBackground() {
        return this.mBackground;
    }
}
