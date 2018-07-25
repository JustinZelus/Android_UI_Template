package kawasaki.icm.com.tw.kawasaki_ui.fragments.online;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.MemoInformationAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.KawasakiList;
import kawasaki.icm.com.tw.kawasaki_ui.model.MemoInformation;


/**
 * Created by icm_mobile on 2018/6/28.
 */

public class MemoFragment_ONLine extends Fragment implements IRecyclerViewClickListener {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<MemoInformation> mData = new ArrayList<>();

    @SuppressLint("ResourceType")
    public void initModel() {
        mData.add(new MemoInformation("Condition","Wet",""));
        mData.add(new MemoInformation("Weather","Cloudy",""));
        mData.add(new MemoInformation("Temperature","623","degC"));
        mData.add(new MemoInformation("Humidity","200","%"));
        mData.add(new MemoInformation("Atmospheric Pressure","130","kPa"));
        mData.add(new MemoInformation("Comment","write message here",""));
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
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_memo_information,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MemoInformationAdapter(mData , context ,this);
        mRecyclerView.setAdapter(mAdapter);
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
        Log.d("Memo", " position : " + position + " clicked");
    }
}
