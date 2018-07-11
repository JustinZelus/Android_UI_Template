package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.model.Menu;

/**
 * Created by icm_mobile on 2018/6/27.
 */

public class Map_Adjustment_Fragment extends Fragment{
    List<Menu> mData = new ArrayList<>();
    Map_Adjustment_Fragment Instance;
    Context context;
    ImageButton btnFI;
    ImageButton btnIG;
    ImageButton btnMappingValue;
    ImageButton btnMemo;
    int myPage = -1;
    /* 按下按鈕有波紋效果(圖片需透明)*/
    boolean isAddRipple = false;

    public static Map_Adjustment_Fragment newInstance(int page){
        Map_Adjustment_Fragment f = new Map_Adjustment_Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",page);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");
        /** menu頁面預設3顆按鈕，按鈕的標題及顏色可在xml裡更改 */
        Resources resources = context.getResources();
        String[] titles = resources.getStringArray(R.array.map_adjustment);
        TypedArray backgrounds = resources.obtainTypedArray(R.array.map_adjustment_Ripples);

        for(int i = 0; i < titles.length; i++) {
            mData.add(new Menu( titles[i] , backgrounds.getDrawable(i)));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        Instance = this;
//        initModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.origin_fragment_map_adjustment_fixed,container,false);

        btnFI = v.findViewById(R.id.btn_FI);
        btnIG = v.findViewById(R.id.btn_IG);
        btnMappingValue = v.findViewById(R.id.btn_MappingValue);
        btnMemo = v.findViewById(R.id.btn_Memo);
        btnFI.setOnClickListener(view_onClick_listener);
        btnIG.setOnClickListener(view_onClick_listener);
        btnMappingValue.setOnClickListener(view_onClick_listener);
        btnMemo.setOnClickListener(view_onClick_listener);
        btnFI.setTag("f1");
        btnIG.setTag("f2");
        btnMappingValue.setTag("f3");
        btnMemo.setTag("f4");
        if(isAddRipple) {
            btnFI.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
            btnIG.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
            btnMappingValue.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
            btnMemo.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
        }



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

    private View.OnClickListener view_onClick_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageButton btn = (ImageButton)v;
            String tag = btn.getTag().toString();
            Fragment des = null;

            switch (tag) {
                case "f1":
                    des = new Table_Offline_Edit_Data_Fragment();
                    break;
                case "f2":
                    des = new Table_Offline_IG_Edit_Data_Fragment();
                    break;
                case "f3":
                    des = new Table_Offline_Map_Point_Setting_Fragment();
                    break;
                case "f4":
                    des = new Table_Offline_Memo_Information_Fragment();
                    break;
                default:
                    break;
            }

            if(des != null)
                MainActivity.Instance.switchFragment(Instance,des);

        }
    };

}
