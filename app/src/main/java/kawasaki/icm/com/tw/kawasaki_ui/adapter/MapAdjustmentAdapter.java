package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Menu;


/**
 * Created by icm_mobile on 2018/6/27.
 */

public class MapAdjustmentAdapter extends RecyclerView.Adapter<MapAdjustmentAdapter.ViewHolder> {
    int height = 0;
    List<Menu> mData;
    Context context;
    static IRecyclerViewClickListener mRecyclerViewClickListener;
    private boolean isAddBackground = false;
    private boolean isAddTypeface = true;
    Typeface font;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.tv_view);
            mTextView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.d("MapAdjustmentAdapter"," " + this.getAdapterPosition());
            mRecyclerViewClickListener.recyclerViewItemClicked(v,this.getAdapterPosition());
        }
    }

    public MapAdjustmentAdapter(List<Menu> mData, MainActivity activity, IRecyclerViewClickListener mRecyclerViewClickListener) {
        this.mData = mData;
        this.context = activity;
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;

         /* 取屏幕的寬度來計算按鈕的寬*/
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height = displayMetrics.heightPixels;
        this.font = Typeface.createFromAsset(activity.getAssets(),"fonts/clean_sports.ttf");
    }

    @Override
    public MapAdjustmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_adjustment_view2,parent,false);
        //將view的高度減半
        int height = parent.getMeasuredHeight() / 2;
        v.getLayoutParams().height = height;
        ViewHolder viewHolder = new ViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MapAdjustmentAdapter.ViewHolder holder, int position) {
        Log.d("onBindViewHolder ","" + position);

        holder.mTextView.setText(mData.get(position).getTitle());
        if(isAddTypeface)
            holder.mTextView.setTypeface(font);
        if(isAddBackground)
            holder.mTextView.setBackground(mData.get(position).getBackground());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
