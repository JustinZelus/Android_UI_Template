package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.DataMonitorAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class OpeningFragment extends Fragment {
    Context context;
    Drawable pic;
    ImageView imageView;
    OpeningFragment Instance;

    public static OpeningFragment newInstance() {
        OpeningFragment f = new OpeningFragment();
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        context = getContext();
        if(pic == null)
            pic = context.getResources().getDrawable(R.drawable.bg_kawasaki, context.getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_opening,container,false);
        imageView = v.findViewById(R.id.img_background);
        if(pic != null)
            imageView.setBackground(pic);
        imageView.setOnClickListener(img_click_listener);

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

    /* 圖片響應 */
    View.OnClickListener img_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.Instance.switchFragment(Instance,MainMenuFragment.newInstance());
        }
    };
}
