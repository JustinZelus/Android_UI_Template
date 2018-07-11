package kawasaki.icm.com.tw.kawasaki_ui.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.FieldSettingAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.enums.TableSettingPattern;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;


/**
 * Created by icm_mobile on 2018/6/28.
 */


public class Z_DeprecatedTable_Offline_Map_Point_Setting_Fragment extends Fragment implements IRecyclerViewClickListener {
    RecyclerView mRecyclerView;
    FieldSettingAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<Table> mData = new ArrayList<>();
    TableSettingPattern pattern;

    int text;
    TextView tvFocus = null;
    Button btnUp;
    Button btnDown;
    int max = 100;
    int min = 0;


    @SuppressLint("ResourceType")
    public void initModel() {
//        Resources resources = context.getResources();
//        int[] titles = resources.getVa(R.array.table_offline_ig_edit_data);
//        for(int i = 0; i < 49; i++) {
            mData.add(new Table("" + "0"));
            mData.add(new Table("" + "20"));
            mData.add(new Table("" + "30"));
            mData.add(new Table("" + "40"));
            mData.add(new Table("" + "60"));
            mData.add(new Table("" + "80"));
            mData.add(new Table("" + "100"));

            mData.add(new Table("" + "10"));
            mData.add(new Table("" + "3.5"));
            mData.add(new Table("" + "3.5"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));

            mData.add(new Table("" + "20"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));

            mData.add(new Table("" + "30"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));

            mData.add(new Table("" + "40"));
            mData.add(new Table("" + "3.5"));
            mData.add(new Table("" + "3.5"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));

            mData.add(new Table("" + "60"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));

            mData.add(new Table("" + "80"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
            mData.add(new Table("" + "3.0"));
//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        pattern = TableSettingPattern.SET_FIELD;
        initModel();

    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_ig_offline,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(context,7);
        mRecyclerView.setLayoutManager(mLayoutManager);
//        mAdapter = new TableAdapter(mData , getContext() ,this, pattern);
        mAdapter = new FieldSettingAdapter(mData,context,this);
        mAdapter.setFirstCellInvisible(context.getResources().getDrawable(R.drawable.test),true);
        mRecyclerView.setAdapter(mAdapter);

        btnUp   = v.findViewById(R.id.btn_Up);
        btnDown = v.findViewById(R.id.btn_down);
        btnUp.setTag("btnUp");
        btnDown.setTag("btnDown");
        btnUp.setOnClickListener(btn_adjust_listener);
        btnDown.setOnClickListener(btn_adjust_listener);


        /**action bar 標題更新*/
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.setToolbarTitle(R.string.offline_edit_data);

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

    @Override
    public void recyclerViewItemClicked(View v, int position) {
        tvFocus = (TextView)v;
        text = Integer.parseInt(tvFocus.getText().toString());
    }

    View.OnClickListener btn_adjust_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(tvFocus == null) return;
            String tag = v.getTag().toString();
            switch (tag) {
                case "btnUp":
                    int val = text += 5;
                    tvFocus.setText("" + constraintNumber(val));
                    break;
                case "btnDown" :
                    int _val = text -= 5;
                    tvFocus.setText("" + constraintNumber(_val));
                    break;
            }
        }
    };

    private int constraintNumber(int val) {
        if(val > max) {
            text = max;
        }
        if(val < min) {
            text = min;
        }
        return text;
    }
}
