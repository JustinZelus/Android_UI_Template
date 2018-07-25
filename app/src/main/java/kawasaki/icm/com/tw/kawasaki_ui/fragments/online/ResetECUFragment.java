package kawasaki.icm.com.tw.kawasaki_ui.fragments.online;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import kawasaki.icm.com.tw.kawasaki_ui.R;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class ResetECUFragment extends Fragment {
    ResetECUFragment Instance;
    Context context;

    public static ResetECUFragment newInstance(){
        ResetECUFragment f = new ResetECUFragment();
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
        View v = inflater.inflate(R.layout.fragment_reset_ecu,container,false);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(context,"not yet,dude",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
