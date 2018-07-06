package kawasaki.icm.com.tw.kawasaki_ui.model;

/**
 * Created by icm_mobile on 2018/6/27.
 */

public class Table {
    public String mTitle;

    public Table(){}
    public Table(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean checkTouchCell(int position) {
        boolean result = false;
        if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6) {
            result = true;

        }
        if(position % 7 == 0) {
            result = true;

        }
        return result;
    }
}
