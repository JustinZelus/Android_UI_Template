package kawasaki.icm.com.tw.kawasaki_ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.KawasakiList;

/**
 * Created by icm_mobile on 2018/7/4.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements IRecyclerViewClickListener{
    private List<KawasakiList> mData;
    private Context context;
    IRecyclerViewClickListener recyclerViewClickListener;

    public ListAdapter(List<KawasakiList> data, MainActivity activity,IRecyclerViewClickListener recyclerViewClickListener) {
        this.context = activity;
        this.mData = data;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout fl_1;
        FrameLayout fl_2;
        FrameLayout fl_3;
        TextView tv_1;
        TextView tv_2;
        TextView tv_3;
        public ViewHolder(View itemView) {
            super(itemView);
            fl_1 = itemView.findViewById(R.id.frame_layout_1);
            fl_2 = itemView.findViewById(R.id.frame_layout_2);
            fl_3 = itemView.findViewById(R.id.frame_layout_3);
            tv_1 = itemView.findViewById(R.id.tv_view_1);
            tv_2 = itemView.findViewById(R.id.tv_view_2);
            tv_3 = itemView.findViewById(R.id.tv_view_3);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position == 0 || position == 1 || position == 2) {
            switch (position) {
                case 0 :

                    break;
                case 1 :
                    holder.tv_2.setText(mData.get(position).getTitle());
                    break;
                case 2 :
                    break;
            }
        }

        if(position % 3 == 0)
            holder.tv_2.setText("2");

        holder.tv_3.setText("3");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void recyclerViewItemClicked(View v, int position) {

    }
}
