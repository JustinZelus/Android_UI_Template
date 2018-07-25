package kawasaki.icm.com.tw.kawasaki_ui.fragments.online;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.FileSelectFragment;


/**
 * Created by icm_mobile on 2018/6/22.
 *
 * 此頁面由多個按鈕(TextView，非Button)排列成水平並佔滿屏幕構成。
 * 按鈕的數量參考至values/arrays.xml中的menuTitles數量
 */

public class FunctionSelectFragment extends Fragment {

    Context context;
    Button btnMapAdjustment;
    Button btnDatamonitor;
    Button btnResetECU;
    FunctionSelectFragment Instance;

    public static FunctionSelectFragment newInstance(){
        FunctionSelectFragment f = new FunctionSelectFragment();
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        context = getContext();
        initModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_function_select,container,false);
        btnMapAdjustment = v.findViewById(R.id.btn_map_adjustment);
        btnDatamonitor = v.findViewById(R.id.btn_data_monitor);
        btnResetECU = v.findViewById(R.id.btn_rest_ecu);

        btnMapAdjustment.setOnClickListener(btn_click_listener);
        btnDatamonitor.setOnClickListener(btn_click_listener);
        btnResetECU.setOnClickListener(btn_click_listener);


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

    View.OnClickListener btn_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment des = null;
            if(v.equals(btnMapAdjustment)) {
                des = FileSelectFragment.newInstance();
            }
            else if(v.equals(btnDatamonitor)) {
                des = DataMonitorFragment.newInstance();
            }
            else if(v.equals(btnResetECU)) {
                des = ResetECUFragment.newInstance();
            }

            if(des != null)
                MainActivity.Instance.switchFragment(Instance,des);
        }
    };


}
