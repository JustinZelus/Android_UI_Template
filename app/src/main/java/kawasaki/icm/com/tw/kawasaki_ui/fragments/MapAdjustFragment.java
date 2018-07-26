package kawasaki.icm.com.tw.kawasaki_ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
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
import kawasaki.icm.com.tw.kawasaki_ui.fragments.offline.FIFragment_OFFLine;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.offline.IGFragment_OFFLine;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.online.FIFragment_ONLine;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.online.IGFragment_ONLine;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.online.MappingValueFragment_ONLine;
import kawasaki.icm.com.tw.kawasaki_ui.model.Menu;

/**
 * Created by icm_mobile on 2018/6/27.
 */

public class MapAdjustFragment extends Fragment{
    List<Menu> mData = new ArrayList<>();
    MapAdjustFragment Instance;
    Context context;
    ImageButton btnFI;
    ImageButton btnIG;
    ImageButton btnMappingValue;
    ImageButton btnMemo;
    int toPage = -1;
    /* 按下按鈕有波紋效果(圖片需透明)*/
    boolean isAddRipple = false;


    public static MapAdjustFragment newInstance(){
        MapAdjustFragment f = new MapAdjustFragment();

        return f;
    }
    public static MapAdjustFragment newInstance(int page){
        MapAdjustFragment f = new MapAdjustFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",page);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        toPage = getArguments().getInt("PAGE");

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        Instance = this;
        initModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map_adjustment_fixed,container,false);

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

            //TODO 需判斷要走offline 還是 online
            switch (tag) {
                case "f1":
                    des = (toPage == Pages.OFF_LINE) ? new FIFragment_OFFLine() : new FIFragment_ONLine();
                    break;
                case "f2":
                    des = (toPage == Pages.OFF_LINE) ? new IGFragment_OFFLine() : new IGFragment_ONLine();
                    break;
                case "f3":
                    des = (toPage == Pages.OFF_LINE) ? new MappingValueFragment_OFFLine() : new MappingValueFragment_ONLine();
                    break;
                case "f4":
                    des = (toPage == Pages.OFF_LINE) ? new MemoFragment() : new MemoFragment();
                    break;
                default:
                    break;
            }

            if(des != null)
                MainActivity.Instance.switchFragment(Instance,des);

        }
    };

}
