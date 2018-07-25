package kawasaki.icm.com.tw.kawasaki_ui.fragments.setup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class SetupFragment extends Fragment {
    SetupFragment Instance;
    Context context;
    Button btnDateTime;
    Button btnDeg;
    Button btnSystemInformation;

    public static SetupFragment newInstance(){
        SetupFragment f = new SetupFragment();
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
        View v = inflater.inflate(R.layout.fragment_setup,container,false);
        btnDateTime = v.findViewById(R.id.btn_datetime_setting);
        btnDeg = v.findViewById(R.id.btn_deg);
        btnSystemInformation = v.findViewById(R.id.btn_system_information);
        btnDateTime.setOnClickListener(btn_click_listener);
        btnDeg.setOnClickListener(btn_click_listener);
        btnSystemInformation.setOnClickListener(btn_click_listener);
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
//            Toast.makeText(context,"no yet , dude ",Toast.LENGTH_SHORT).show();
            //TODO 尚未寫code
//            Fragment des = null;
            if(v.equals(btnDateTime)) {
               MainActivity.Instance.startActivityForResult(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 0);
            }
//            else if(v.equals(btnONLine)) {
//                des = ONLine_Fragment.newInstance();
//            }
//            else if(v.equals(btnSetup)) {
//                des =
//            }
//
//            if(des != null)
//                MainActivity.Instance.switchFragment(Instance,des);
        }
    };
}
