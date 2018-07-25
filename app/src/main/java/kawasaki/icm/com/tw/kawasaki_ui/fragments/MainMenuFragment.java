package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.fragment.online.ONLineModelYearFragment;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class MainMenuFragment extends Fragment{
    Context context;
    Button btnOFFLine;
    Button btnONLine;
    Button btnSetup;
    MainMenuFragment Instance;

    public static MainMenuFragment newInstance(){
        MainMenuFragment f = new MainMenuFragment();
        return f;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu,container,false);
        btnOFFLine = v.findViewById(R.id.btn_offline);
        btnONLine = v.findViewById(R.id.btn_online);
        btnSetup = v.findViewById(R.id.btn_setup);

        btnOFFLine.setOnClickListener(btn_click_listener);
        btnONLine.setOnClickListener(btn_click_listener);
        btnSetup.setOnClickListener(btn_click_listener);
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
            if(v.equals(btnOFFLine)) {
                des = OFFLineModelYearFragment.newInstance(Pages.OFF_LINE);
            }
            else if(v.equals(btnONLine)) {
                des = ONLineModelYearFragment.newInstance();
            }
            else if(v.equals(btnSetup)) {
                des = SetupFragment.newInstance();
            }

            if(des != null)
                MainActivity.Instance.switchFragment(Instance,des);
        }
    };
}
