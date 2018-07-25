package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import kawasaki.icm.com.tw.kawasaki_ui.adapter.Z_deprecatedMenuAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Menu;


/**
 * Created by icm_mobile on 2018/6/22.
 *
 * 此頁面由多個按鈕(TextView，非Button)排列成水平並佔滿屏幕構成。
 * 按鈕的數量參考至values/arrays.xml中的menuTitles數量
 */

public class Z_DeprecatedMenu_2_Button_Fragment extends Fragment implements IRecyclerViewClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<Menu> mData = new ArrayList<>();
    int myPage = -1;

    public static Z_DeprecatedMenu_2_Button_Fragment newInstance(int myPage){
        Z_DeprecatedMenu_2_Button_Fragment f = new Z_DeprecatedMenu_2_Button_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",myPage);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");
        Resources resources = context.getResources();
        String[] titles = resources.getStringArray(R.array.menu_2_button_Titles);
        for(int i = 0; i < titles.length; i++) {
            mData.add(new Menu( titles[i] , resources.getDrawable(R.drawable.ripple_rectangle_rounded)));
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
        View v = inflater.inflate(R.layout.z_deprecatedfragment_menu,container,false);

        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為橫向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Z_deprecatedMenuAdapter(mData,(MainActivity) getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        /** action bar 標題更新 */
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.updateToolbar(Pages.MENU_2_BUTTON);

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

    //Offline,Online,Setup 三顆按鈕的響應
    @Override
    public void recyclerViewItemClicked(View v, int position) {
        Log.d("recyclerViewItemClicked","position - " + position+ " , " + ((TextView)v).getText() + "\r\n");

        Fragment des = null;
        switch(position) {
            case 0:
                des = SampleFragment.newInstance(myPage);
                break;
            case 1:
                des = UserLibraryFragment.newInstance(myPage);
                break;
        }

        if(des != null)
            MainActivity.Instance.switchFragment(this,des);
    }


}
