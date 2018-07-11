package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.KawasakiList;


/**
 * Created by icm_mobile on 2018/6/28.
 */

public class Z_DeprecatedTable_Offline_Memo_Information_Fragment extends Fragment implements IRecyclerViewClickListener {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<KawasakiList> mData = new ArrayList<>();



    @SuppressLint("ResourceType")
    public void initModel() {
        Resources resources = context.getResources();
        int[] titles = resources.getIntArray(R.array.table_offline_edit_data);
//        for(int i = 0; i < 21; i++) {
        mData.add(new KawasakiList("Memo Item"));
        mData.add(new KawasakiList("Value"));
        mData.add(new KawasakiList("Unit"));

        mData.add(new KawasakiList("Condition"));
        mData.add(new KawasakiList("Wet"));
        mData.add(new KawasakiList(""));

        mData.add(new KawasakiList("Weather"));
        mData.add(new KawasakiList("Cloudy"));
        mData.add(new KawasakiList(""));

        mData.add(new KawasakiList("Temperature"));
        mData.add(new KawasakiList("623"));
        mData.add(new KawasakiList("degC"));

        mData.add(new KawasakiList("Humidity"));
        mData.add(new KawasakiList("200"));
        mData.add(new KawasakiList("%"));

        mData.add(new KawasakiList("Atmospheric Pressure"));
        mData.add(new KawasakiList("130"));
        mData.add(new KawasakiList("kPa"));

        mData.add(new KawasakiList("Comment"));
        mData.add(new KawasakiList("^___^"));
        mData.add(new KawasakiList(""));
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();

        initModel();

    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_memo_offline,container,false);
//        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
//
//        mRecyclerView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(context);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new ListAdapter(mData , (MainActivity)context ,this);
//        mRecyclerView.setAdapter(mAdapter);

        /**action bar 標題更新*/
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.setToolbarTitle(R.string.offline_edit_data);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void recyclerViewItemClicked(View v, int position) {


    }
}
