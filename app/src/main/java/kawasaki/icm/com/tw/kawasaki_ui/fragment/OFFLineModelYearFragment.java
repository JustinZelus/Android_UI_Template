package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



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

public class OFFLineModelYearFragment extends Fragment implements IRecyclerViewClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<Directory> mData = new ArrayList<>();
    int myPage = -1;

    public static OFFLineModelYearFragment newInstance(int myPage){
        OFFLineModelYearFragment f = new OFFLineModelYearFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",myPage);
        f.setArguments(bundle);
        return f;
    }

    /*** 退回上一步，會pop掉當前的fragment。
     * data若有做刪除等動作，data庫也必須做更新。下次拿到的data才會是正確的 */
    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");
        Resources resources = context.getResources();
        String[] titles = null;
        switch (myPage) {
            case -1:
                break;
            case 0:
                titles = resources.getStringArray(R.array.offline_directory_name);
            case 1:
//                titles = resources.getStringArray(R.array.online_directory_name);
                break;
        }
        for(int i = 0; i < titles.length; i++) {
            mData.add(new Directory( titles[i] , resources.getDrawable(R.drawable.ripple_directory_0,context.getTheme())));
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
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_model_year_offline,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        mAdapter = new DirectoryAdapter(mData , (MainActivity)getContext() ,this);
        mRecyclerView.setAdapter(mAdapter);

        /**action bar 標題更新*/
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.updateToolbar(Pages.DIRECTORY_KXF);


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
        Log.d("recyclerViewItemClicked","position - " + position+ " , " + ((TextView)v).getText() + "\r\n");
        Fragment des = FileSelectFragment.newInstance();
        if(des != null)
            MainActivity.Instance.switchFragment(this,des);

    }
}
