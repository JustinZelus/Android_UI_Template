package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Directory;


/**
 * Created by icm_mobile on 2018/6/22.
 */

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.ViewHolder> {

    List<Directory> mData;
    int width = 0;
    Context context;
    static IRecyclerViewClickListener mRecyclerViewClickListener;
    private boolean isAddBackground = false;
    private boolean isAddTypeface = false;
    private boolean isAddTextColor = false;
    Typeface font;

    int cell_Height = 0;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.tv_view);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mRecyclerViewClickListener.recyclerViewItemClicked(v,this.getAdapterPosition());
        }
    }



    public DirectoryAdapter(List<Directory> mData, MainActivity activity, IRecyclerViewClickListener mRecyclerViewClickListener) {
        this.mData = mData;
        this.context = activity;
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
        this.font = Typeface.createFromAsset(activity.getAssets(),"fonts/sports_world_regular.ttf");
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.directory_text_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        if(cell_Height == 0) {
            cell_Height = parent.getHeight() / mData.size(); /* 總共有7列*/
        }
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText(mData.get(position).getTitle());
        if(isAddTextColor)
            holder.mTextView.setTextColor(context.getResources().getColor(R.color.directory));
        if(isAddTypeface)
            holder.mTextView.setTypeface(font);
        if(isAddBackground)
            holder.mTextView.setBackground(mData.get(position).getBackground());
//        holder.mTextView.setBackground( (position % 2) == 0 ? mData.get(position).getBackground_0() : mData.get(position).getBackground_1());
        holder.mTextView.getLayoutParams().height = cell_Height;

    }

    public void removeItem(int position) {
        mData.remove(position);

        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



}
