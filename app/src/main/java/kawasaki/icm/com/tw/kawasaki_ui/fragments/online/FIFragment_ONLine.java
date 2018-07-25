package kawasaki.icm.com.tw.kawasaki_ui.fragments.online;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.CellSettingAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Table;


/**
 * Created by icm_mobile on 2018/6/28.
 */

public class FIFragment_ONLine extends Fragment implements IRecyclerViewClickListener {
    RecyclerView mRecyclerView;
    CellSettingAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<Table> mData = new ArrayList<>();
//    TableSettingPattern pattern;

    int text;
    TextView tvFocus = null;
    Button btnUp;
    Button btnDown;
    int max = 50;
    int min = -50;



    @SuppressLint("ResourceType")
    public void initModel() {
        Resources resources = context.getResources();
        int[] titles = resources.getIntArray(R.array.table_offline_edit_data);
        for(int i = 0; i < 49; i++) {
            mData.add(new Table("" + titles[i]));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
//        pattern = TableSettingPattern.SET_CELL;
        initModel();

    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fi_online,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(context,7);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new TableAdapter(mData , getContext() ,this, pattern);
        mAdapter = new CellSettingAdapter(mData,context,this);
        mAdapter.setFirstCellInvisible(context.getResources().getDrawable(R.drawable.bg_cell_0),true);
        mRecyclerView.setAdapter(mAdapter);

        btnUp   = v.findViewById(R.id.btn_Up);
        btnDown = v.findViewById(R.id.btn_down);
        btnUp.setTag("btnUp");
        btnDown.setTag("btnDown");
        btnUp.setOnClickListener(btn_adjust_listener);
        btnDown.setOnClickListener(btn_adjust_listener);
        btnDown.setOnTouchListener(btn_adjust_press_listener);
        btnUp.setOnTouchListener(btn_adjust_press_listener);


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
        text =Integer.parseInt(tvFocus.getText().toString());
        Log.i(" text ", " " + text);

    }

    boolean isButtonLongPressed = true;

    //TODO 2018/07/04 1.Down按鈕的長壓事件(需查看是否這樣寫會影響UI效能，是否須加handler \ ui thread ?)，數值需累加並更新UI。
    //TODO 2018/07/05 1.ONLine 1個畫面未做。 2.delete按鈕及table下方4顆按鈕未做。
    //TODO 2018/07/06 1.依據流程圖重新命名類別。2.重構相同功能的畫面(繼承)。3.4個按鈕圖片的尺寸
    //TODO 2018/07/09 1.抓出重複的listener，單獨寫成一個類。

    View.OnTouchListener btn_adjust_press_listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.d(" event ", " " + event.getAction());
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
//                    if(tvFocus != null) {
////                        isButtonLongPressed = true;
//
//                        if (v.getTag().equals("btnDown"))
//                            overConstraintNumber(--text);
//                        else
//                            overConstraintNumber(++text);
//
//                        tvFocus.setText("" + text);
//                    }
                    Log.i("event","   ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_UP:
                    isButtonLongPressed = false;

                    Log.d("event","   ACTION_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("event","   ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    Log.d("event","   ACTION_POINTER_DOWN");
                    break;
            }
            /** onTouch方法返回值是false（事件未被消费，向下傳遞）時，onTouchEvent方法才被執行，也就是OnＣlick會觸發。
             *  onTouch方法返回值是true（事件被消费）時，則onTouchEvent方法將不會被執行，OnClick最終不會被觸發。
             * */


            return false;

        }
    };

    View.OnClickListener btn_adjust_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            isButtonLongPressed = true;

            if(tvFocus == null) return;
            String tag = v.getTag().toString();
            switch (tag) {
                case "btnUp":
//                    if(overConstraintNumber(++text))
//                        return;
                    tvFocus.setText("" + contraintNumber(++text));
                    break;
                case "btnDown" :
//                    if(overConstraintNumber(--text))
//                        return;

                    tvFocus.setText("" + contraintNumber(--text));
                    break;
            }
//            Log.i(" text ", " " + text);
        }
    };

    private int contraintNumber(int val) {
        if(val > max) {
            text = max;
        }
        if(val < min) {
            text = min;
        }
        return text;
    }

    private boolean overConstraintNumber(int val) {
//        Log.i(" text ", " " + text);
        boolean result = false;
        if(val > max) {
            result = true;
            text = max;
        }
        if(val < min) {
            result = true;
            text = min;
        }
        return result;
    }
}
