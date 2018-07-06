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
    ImageButton btn_f1_edit_data;
    ImageButton btn_f2_ig_edit_data;
    ImageButton btn_f3_map_point_setting;
    ImageButton btn_f4_memo_information;
    int myPage = -1;

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
        View v = inflater.inflate(R.layout.origin_fragment_map_adjustment,container,false);

        btn_f1_edit_data         = v.findViewById(R.id.btn_f1_edit_data);
        btn_f2_ig_edit_data      = v.findViewById(R.id.btn_f2_ig_edit_data);
        btn_f3_map_point_setting = v.findViewById(R.id.btn_f3_map_point_setting);
        btn_f4_memo_information  = v.findViewById(R.id.btn_f4_memo_information);
        btn_f1_edit_data.setOnClickListener(view_onClick_listener);
        btn_f2_ig_edit_data.setOnClickListener(view_onClick_listener);
        btn_f3_map_point_setting.setOnClickListener(view_onClick_listener);
        btn_f4_memo_information.setOnClickListener(view_onClick_listener);
        btn_f1_edit_data.setTag("f1");
        btn_f2_ig_edit_data.setTag("f2");
        btn_f3_map_point_setting.setTag("f3");
        btn_f4_memo_information.setTag("f4");
        btn_f1_edit_data.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
        btn_f2_ig_edit_data.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
        btn_f3_map_point_setting.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));
        btn_f4_memo_information.setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryLight));



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
