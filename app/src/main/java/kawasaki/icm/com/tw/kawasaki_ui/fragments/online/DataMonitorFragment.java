package kawasaki.icm.com.tw.kawasaki_ui.fragments.online;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.DataMonitorAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.model.DataMonitor;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class DataMonitorFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    private boolean isAddItemDivider = true;
    List<DataMonitor> mData = new ArrayList<>();
    int myPage = -1;

    public static DataMonitorFragment newInstance(){
        DataMonitorFragment f = new DataMonitorFragment();
//        Bundle bundle = new Bundle();
//        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {

//        for(int i = 0; i < 8; i++) {
            mData.add(new DataMonitor("TH-Angle",110.7f,"deg"));
            mData.add(new DataMonitor("ENG-Boost",126.1f,"kPa"));
            mData.add(new DataMonitor("Coolant_TEMP",54f,"degC"));
            mData.add(new DataMonitor("Air_TEMP",26f,"degC"));
            mData.add(new DataMonitor("IG_Offset",0.0f,"deg"));
            mData.add(new DataMonitor("Fi_Adjust",0f,"%"));
            mData.add(new DataMonitor("Gear",1f,""));
            mData.add(new DataMonitor("Battery",11.6f,"V"));
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        initModel();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_data_monitor,container,false);
        mRecyclerView = v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DataMonitorAdapter(mData,context);
        mRecyclerView.setAdapter(mAdapter);


        /**action bar 標題更新*/
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.updateToolbar(Pages.DIRECTORY_SAMPLE);
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

}
