package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.custom.Tools;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Menu;


/**
 * Created by icm_mobile on 2018/6/22.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    int width = 0;
    int height = 0;
    int margin = 0;
    RecyclerView.LayoutParams lp;

    List<Menu> mData;
    Context context;
    static IRecyclerViewClickListener mRecyclerViewClickListener;
    Typeface font;
    /** 是否加入運動風字體 */
    boolean isAddSportFont = false;
    /** 是否加入背景及ripple效果(兩個是綁再一起的) */
    boolean isAddRipple = true;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRecyclerViewClickListener.recyclerViewItemClicked(v,this.getAdapterPosition());
        }
    }



    public MenuAdapter(List<Menu> mData, MainActivity activity, IRecyclerViewClickListener mRecyclerViewClickListener) {
        this.mData = mData;
        this.context = activity;
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.width = displayMetrics.widthPixels; /* 取屏幕的寬度來計算按鈕的寬*/
        this.height = displayMetrics.heightPixels; /* 取屏幕的高來計算按鈕的高*/
        this.font = Typeface.createFromAsset(activity.getAssets(),"fonts/clean_sports.ttf");
        this.margin = height / 60; /* 每個button之間的間距*/

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.origin_menu_text_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(tv);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mData.get(position).getTitle());
        if(isAddSportFont)
            holder.mTextView.setTypeface(font);
        if(isAddRipple)
            holder.mTextView.setBackground(mData.get(position).getBackground());
        holder.mTextView.getLayoutParams().height = (height - MainActivity.Instance.getStatusBarHeight() - Tools.convertDpToPixel(context,margin * 2))/3;
        // Get the TextView current LayoutParams
        lp = (RecyclerView.LayoutParams) holder.mTextView.getLayoutParams();
        // Set TextView layout margin 25 pixels to all side, Left Top Right Bottom Margi
        lp.setMargins(margin,margin,margin,margin);
        // Apply the updated layout parameters to TextView
        holder.mTextView.setLayoutParams(lp);


//        holder.mTextView.getLayoutParams().width = width/mData.size();
//        holder.mTextView.setBackground(mData.get(position).getBackground());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

//    public int convertDpToPixel(int dp){
//        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
//        return (int)px;
//    }

}
