package kawasaki.icm.com.tw.kawasaki_ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.base.BaseFragment;

/**
 * Created by icm_mobile on 2018/7/11.
 */

public class OpeningFragment extends BaseFragment {
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
        Log.d("flow-childFragment:","onCreate"  );
        Instance = this;
        context = getContext();
        if(pic == null)
            pic = context.getResources().getDrawable(R.drawable.bg_kawasaki, context.getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("flow-childFragment:","onCreateView"  );
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
        Log.d("flow-childFragment:","onResume"  );
        setCurrentPage(Pages.ON_LINE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("flow-childFragment:","onDestroy"  );
    }

    /* 圖片響應 */
    View.OnClickListener img_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.Instance.switchFragment(Instance,MainMenuFragment.newInstance());
        }
    };
}
