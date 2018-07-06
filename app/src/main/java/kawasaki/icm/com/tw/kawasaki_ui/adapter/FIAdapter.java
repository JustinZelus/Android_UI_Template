package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;

/**
 * Created by icm_mobile on 2018/7/6.
 */

public class FIAdapter extends TableAdapter {
    public FIAdapter(List<Table> mData, Context context, IRecyclerViewClickListener recyclerViewClickListener, TableSettingPattern pattern) {
        super(mData, context, recyclerViewClickListener, pattern);

    }
}
