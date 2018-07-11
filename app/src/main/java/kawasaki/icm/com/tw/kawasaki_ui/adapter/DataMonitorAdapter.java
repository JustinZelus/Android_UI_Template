package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.model.DataMonitor;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class DataMonitorAdapter extends RecyclerView.Adapter<DataMonitorAdapter.ViewHolder>  {
    public List<DataMonitor> mData;
    Context context;
    int row_Height = 0;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        TextView tvValue;
        TextView tvUnit;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_item);
            tvValue = itemView.findViewById(R.id.tv_value);
            tvUnit = itemView.findViewById(R.id.tv_unit);
        }
    }

    public DataMonitorAdapter(List<DataMonitor> data , Context context) {
        this.mData = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_datamonitor,parent,false);

        ViewHolder viewHolder = new ViewHolder(v);

        if(row_Height == 0) {
            row_Height = parent.getHeight() / mData.size(); /* 總共有8列*/
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvItem.setText("" + mData.get(position).getItem());
        holder.tvValue.setText("" + mData.get(position).getValue());
        holder.tvUnit.setText("" + mData.get(position).getUnit());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
