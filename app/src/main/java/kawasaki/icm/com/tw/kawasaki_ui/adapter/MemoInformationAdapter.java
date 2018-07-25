package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.DataMonitor;
import kawasaki.icm.com.tw.kawasaki_ui.model.MemoInformation;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class MemoInformationAdapter extends RecyclerView.Adapter<MemoInformationAdapter.ViewHolder>  {
    public List<MemoInformation> mData;
    Context context;
    int row_Height = 0;
    static int index;
    static IRecyclerViewClickListener recyclerViewClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvItem;
        TextView tvValue;
        TextView tvUnit;
        LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            tvValue = itemView.findViewById(R.id.tv_value);
//            tvValue.setTag(index++);
            tvUnit = itemView.findViewById(R.id.tv_unit);
            linearLayout = itemView.findViewById(R.id.parent_linearlayout);
            linearLayout.setOnClickListener(this);
//            tvItem.setOnClickListener(this);
//            tvValue.setOnClickListener(this);
//            tvUnit.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.recyclerViewItemClicked(v,this.getAdapterPosition());
        }
    }

    public MemoInformationAdapter(List<MemoInformation> data , Context context, IRecyclerViewClickListener recyclerViewClickListener) {
        this.mData = data;
        this.context = context;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_memo_information,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        if(row_Height == 0) {
            row_Height = (int)(parent.getHeight() * 1.0f) / mData.size() ;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvItem.setText("" + mData.get(position).getItem());
        holder.tvValue.setText("" + mData.get(position).getValue());
        holder.tvValue.setBackground(context.getResources().getDrawable(R.drawable.ripple_rectangle));
        holder.tvUnit.setText("" + mData.get(position).getUnit());
//        holder.linearLayout.getLayoutParams().height = row_Height;
        holder.tvItem.getLayoutParams().height = row_Height;
        holder.tvValue.getLayoutParams().height = row_Height;
        holder.tvUnit.getLayoutParams().height = row_Height;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
