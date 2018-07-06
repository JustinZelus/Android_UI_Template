package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.DirectoryAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Directory;


/**
 * Created by icm_mobile on 2018/6/22.
 */

public class Sample_Fragment extends Fragment implements IRecyclerViewClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    private boolean isAddItemDivider = true;
    List<Directory> mData = new ArrayList<>();
    int myPage = -1;

    public static Sample_Fragment newInstance(int page){
        Sample_Fragment f = new Sample_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",page);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");
        Resources resources = context.getResources();
        TypedArray backgrounds = resources.obtainTypedArray(R.array.directoryRipples);
        String[] titles = null;
        titles = resources.getStringArray(R.array.sample_directory_name_offline);
        for(int i = 0; i < titles.length; i++) {
            mData.add(new Directory( titles[i] , resources.getDrawable(R.drawable.ripple_directory_0)));
        }
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
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_directory,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DirectoryAdapter(mData,(MainActivity)getContext(),this);
        mRecyclerView.setAdapter(mAdapter);
        if(isAddItemDivider)
            mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

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

    @Override
    public void recyclerViewItemClicked(View v, int position) {
        Fragment des = Map_Adjustment_Fragment.newInstance(myPage);
        if(des != null)
            MainActivity.Instance.switchFragment(this,des);
    }
}
