package kawasaki.icm.com.tw.kawasaki_ui.model;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class DataMonitor {
    String item;
    float mValue;
    String unit;

    public DataMonitor(String item,float mValue,String unit) {
        this.item = item;
        this.mValue = mValue;
        this.unit = unit;
    }

    public String getItem() {
        return item;
    }
    public float getValue() {
        return mValue;
    }
    public String getUnit() { return unit;}
}
