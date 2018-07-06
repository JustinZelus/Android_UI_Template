package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.custom.MyPaint;
import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.TableItemListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;

/**
 * Created by icm_mobile on 2018/7/6.
 */

public class FieldSettingAdapter extends BaseTableAdapter {
    public FieldSettingAdapter(List<Table> mData, Context context, IRecyclerViewClickListener recyclerViewClickListener) {
        super(mData, context, recyclerViewClickListener);
    }

    @Override
    public void setCellAppearance(TextView tv, int position) {
        if(checkTouchCell(position)) {
            tv.setText(mData.get(position).getTitle());
        }
        else {
            tv.setBackground(context.getResources().getDrawable(R.drawable.rectangle_disable));
        }
    }

    @Override
    public void setTextViewListener(TextView tv,final int position) {
        tv.setOnClickListener(new TableItemListener(recyclerViewClickListener,position,context,this) {
            @Override
            protected boolean checkCellPattern() {
                if(!checkTouchCell(position))
                    return true;
                return false;
            }
        });
    }

}
