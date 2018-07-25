package kawasaki.icm.com.tw.kawasaki_ui.model;

/**
 * Created by icm_mobile on 2018/7/12.
 */

public class MemoInformation {
    String item;
    String mValue;
    String unit;
    public MemoInformation(String item, String mValue, String unit) {
        this.item = item;
        this.mValue = mValue;
        this.unit = unit;
    }
    public String getItem() {
        return item;
    }
    public String getValue() {
        return mValue;
    }
    public String getUnit() {
        return unit;
    }
}
