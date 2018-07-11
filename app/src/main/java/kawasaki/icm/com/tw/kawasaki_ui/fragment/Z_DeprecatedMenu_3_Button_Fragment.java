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

public class Z_DeprecatedMenu_3_Button_Fragment extends Fragment implements IRecyclerViewClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<Menu> mData = new ArrayList<>();
    int myPage = -1;

    public static Z_DeprecatedMenu_3_Button_Fragment newInstance(int myPage){
        Z_DeprecatedMenu_3_Button_Fragment f = new Z_DeprecatedMenu_3_Button_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",myPage);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");

        /** menu頁面預設3顆按鈕，按鈕的標題及顏色可在xml裡更改 */
        Resources resources = context.getResources();
        String[] titles = null;

        switch (myPage) {
            case -1:
                titles = resources.getStringArray(R.array.menu_3_button_Titles);
                break;
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }

        for(int i = 0; i < 3; i++) {
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
        mAdapter = new Z_deprecatedMenuAdapter(mData, (MainActivity) getContext(),this);
        mRecyclerView.setAdapter(mAdapter);

        /** action bar 標題更新 */
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.updateToolbar(Pages.MENU_3_BUTTON);

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
                des = OFFLineModelYearFragment.newInstance(Pages.OFF_LINE);
                break;
            case 1:
                des = ONLineModelYearFragment.newInstance();
                break;
            case 2:
                break;
        }

        if(des != null)
            MainActivity.Instance.switchFragment(this,des);

//        if(des != null) {
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out); //必須用自定義動畫才有效果，原因https://www.e-learn.cn/content/wangluowenzhang/89903
//            if(!des.isAdded()) {
//                fragmentTransaction.add(R.id.fragment_container,des);
//            }
//            else
//               fragmentTransaction.replace(R.id.fragment_container, des);
//
//            if(needToAddBackStack)
//                fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        }


    }

    boolean needToAddBackStack = true;
}
