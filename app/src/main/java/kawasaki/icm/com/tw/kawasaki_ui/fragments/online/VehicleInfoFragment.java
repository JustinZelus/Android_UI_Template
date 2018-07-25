package kawasaki.icm.com.tw.kawasaki_ui.fragment.online;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class VehicleInfoFragment extends Fragment {
    Context context;
    LinearLayout linearLayout;
    VehicleInfoFragment Instance;

     static VehicleInfoFragment newInstance() {
        VehicleInfoFragment f = new VehicleInfoFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        Instance = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_vehicle_info,container,false);
        linearLayout = v.findViewById(R.id.linear_layout);
        linearLayout.setOnClickListener(listener);


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

    /** 畫面點擊的響應，切至下一個頁面*/
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("Vehicle_Fragment"," clicked !");
            Fragment des = new FunctionSelectFragment();
            if(des != null)
                MainActivity.Instance.switchFragment(Instance,des);
        }
    };
}
