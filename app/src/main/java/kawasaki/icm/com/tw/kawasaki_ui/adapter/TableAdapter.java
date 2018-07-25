package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.custom.MyPaint;
import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;


/**
 * Created by icm_mobile on 2018/6/27.
 *
 * 上方第一排與左邊第一排都是欄位名稱
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder>{

    private List<Table> mData;
    private Context context;
    private IRecyclerViewClickListener recyclerViewClickListener;

    private  TextView lastTextView;
    private int cell_Height = 0; /* each cell's height*/
    private TableSettingPattern pattern;
    public  TableAdapter Instance;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        FrameLayout frameLayout;

        public ViewHolder(View v) {
            super(v);

            if(v != null) {
                frameLayout = v.findViewById(R.id.frame_layout);
                textView = v.findViewById(R.id.tv_view);
//                textView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
//            Log.d("TableAdapter onClick : "," " + this.getAdapterPosition());
//            int position = this.getAdapterPosition();
//
//            switch (pattern) {
//                case SET_CELL:
//                    setTableCell(position);
//
//                case SET_FIELD:
//                    setTableField(position);
//            }
//
//            TextView currentTextView = (TextView)v;
//            recyclerViewClickListener.recyclerViewItemClicked(currentTextView,position);
//            if(currentTextView.equals(lastTextView)) return;
//            if(lastTextView != null)
//                lastTextView.setBackground(getOriginalColor());
//            currentTextView.setBackground(getHighlightColor());
//            lastTextView = currentTextView;
        }



    }

    public TableAdapter(List<Table> mData ,Context context,IRecyclerViewClickListener recyclerViewClickListener,TableSettingPattern pattern) {
        Instance = this;
        this.mData = mData;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.pattern = pattern;

    }

    private int height = 0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.origin_table_view,parent,false);

        if(cell_Height == 0) {
            cell_Height = parent.getHeight() / 7; /* 總共有7列*/
        }

        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        /*第0個設圖片*/
        if(position == 0) {
            Log.i("onBindViewHolder", "  0 hava to set backgroud");
            holder.frameLayout.setBackground(context.getDrawable(R.drawable.bg_cell_0));
            holder.textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            /**Map Point Setting是設定欄位值，其它兩個是設定cell值*/
            if(pattern == TableSettingPattern.SET_FIELD) {
                if(checkTouchCell(position)) {
                    holder.textView.setText(mData.get(position).getTitle());
                }
                else {
                    holder.textView.setBackground(context.getResources().getDrawable(R.drawable.rectangle_disable));
                }
            }
            else {
                holder.textView.setText(mData.get(position).getTitle());
            }
        }

        /* 版面自適應性 - cell高 */
        holder.frameLayout.getLayoutParams().height = cell_Height;
        /* each cell listener ，會先判斷點擊的view為欄位或cell*/
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TableAdapter onClick : "," " + position);
                switch (pattern) {
                    case SET_CELL:
                        if(checkTouchCell(position))
                            return;
                        break;
                    case SET_FIELD:
                        if(!checkTouchCell(position))
                            return;
                        break;
                }

                /* 將目前點選的textview設為高亮，上一個恢復原色*/
                TextView currentTextView = (TextView)v;
                 /* 調整數值的listener */
                recyclerViewClickListener.recyclerViewItemClicked(currentTextView,position);
                if(currentTextView.equals(lastTextView)) return; /* 點選到一樣的則不動 */
                if(lastTextView != null)
                    lastTextView.setBackground(MyPaint.getOriginalColor(context));
                    currentTextView.setBackground(MyPaint.getHighlightColor(context));
                lastTextView = currentTextView;

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private boolean checkTouchCell(int position) {
        boolean result = false;
        if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 6) {
            result = true;

        }
        if(position % 7 == 0) {
            result = true;

        }
        return result;
    }

    private boolean checkTouchField(int position) {
        boolean result = false;

        if(position % 7 != 0) {
            result = true;
        }

        if(position != 1 || position != 2 || position != 3 || position != 4 || position != 5 || position != 6) {
            result = true;
        }

        return  result;
    }
}
