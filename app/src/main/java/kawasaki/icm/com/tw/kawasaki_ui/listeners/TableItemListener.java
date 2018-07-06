package kawasaki.icm.com.tw.kawasaki_ui.listeners;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import kawasaki.icm.com.tw.kawasaki_ui.adapter.BaseTableAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.custom.MyPaint;
import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;


/**
 * Created by icm_mobile on 2018/7/6.
 */

public abstract class TableItemListener implements View.OnClickListener {

    IRecyclerViewClickListener recyclerViewClickListener;
    int position;
    Context context;
    BaseTableAdapter adapter;

    public TableItemListener(IRecyclerViewClickListener recyclerViewClickListener,final int position, Context context,BaseTableAdapter adapter) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.position = position;
        this.context = context;
        this.adapter = adapter;
    }

    protected abstract boolean checkCellPattern();

    @Override
    public void onClick(View v) {
        if(checkCellPattern()) return;
        recyclerViewClickListener.recyclerViewItemClicked(v,position);

        /* 將目前點選的textView設為高亮，上一個恢復原色*/
        TextView tv = (TextView)v;
        if(adapter.lastTextView == null) {
            adapter.lastTextView = tv;
            adapter.lastTextView.setBackground(MyPaint.getHighlightColor(context));
        }
        else{
            if(tv.equals(adapter.lastTextView)) return;
            adapter.lastTextView.setBackground(MyPaint.getOriginalColor(context));
            tv.setBackground(MyPaint.getHighlightColor(context));
            adapter.lastTextView = tv;
        }

    }
}
