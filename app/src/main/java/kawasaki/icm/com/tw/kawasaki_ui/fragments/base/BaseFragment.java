package kawasaki.icm.com.tw.kawasaki_ui.fragments.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.SampleFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.UserLibraryFragment;


/**
 * Created by icm_mobile on 2018/6/22.
 *
 * 此頁面由多個按鈕(TextView，非Button)排列成水平並佔滿屏幕構成。
 * 按鈕的數量參考至values/arrays.xml中的menuTitles數量
 */

public abstract class BaseFragment extends Fragment{

    protected void setCurrentPage(int page) {
        Log.d("flow-setPage:","" + page);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("flow-BaseFragment:","onCreate"  );
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Log.d("flow-BaseFragment:","OnCreateView"  );
//
//        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_opening,container,false);
//
//
//        return v;
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("flow-BaseFragment:","onResume"  );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("flow-BaseFragment:","onDestroy"  );
    }

}
