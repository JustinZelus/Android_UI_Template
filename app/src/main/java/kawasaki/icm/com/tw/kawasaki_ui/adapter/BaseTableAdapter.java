package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.TableItemListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;

/**
 * Created by icm_mobile on 2018/7/6.
 */

public abstract class BaseTableAdapter extends RecyclerView.Adapter<BaseTableAdapter.ViewHolder> {
    protected List<Table> mData;
    protected Context context;
    protected IRecyclerViewClickListener recyclerViewClickListener;
    /* set (0,0) cell to invisible*/
    private boolean isFirstCellInVisible = false;
    /* each cell's height */
    private int cell_Height = 0;
    /* row count */
    private int rowCount = 7;
    /* cell background */
    private Drawable cellPic;
    public TextView lastTextView;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        FrameLayout frameLayout;

        public ViewHolder(View v) {
            super(v);
            if(v != null) {
                frameLayout = v.findViewById(R.id.frame_layout);
                textView    = v.findViewById(R.id.tv_view);
            }
        }
    }

    public BaseTableAdapter(List<Table> mData ,Context context,IRecyclerViewClickListener recyclerViewClickListener) {
        this.mData = mData;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public BaseTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.origin_table_view,parent,false);
        /* 根據row的數量，計算cell的高*/
        if(cell_Height == 0) {
            cell_Height = parent.getHeight() / rowCount;
        }

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    public abstract void setCellAppearance(TextView tv,int position);
    public abstract void setTextViewListener(TextView tv,int position);

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(position == 0 && isFirstCellInVisible) {
            holder.frameLayout.setBackground(cellPic);
            holder.textView.setVisibility(View.INVISIBLE);
        }

        /* cell的外觀(子類覆寫) */
        setCellAppearance(holder.textView,position);
        /* 版面自適應性 - cell高 */
        holder.frameLayout.getLayoutParams().height = cell_Height;
        /* cell的響應(子類覆寫) */
        setTextViewListener(holder.textView,position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setFirstCellInvisible(Drawable pic , boolean isInvisible) {
        if(pic != null && isInvisible) {
            isFirstCellInVisible = true;
            cellPic = pic;
        }
    }

    public void setLastClickView(TextView tv) {
        lastTextView = tv;
    }
    public TextView getLastTextView() {
        return lastTextView;
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

    private void setRow(int count) {
        this.rowCount = count;
    }




}
