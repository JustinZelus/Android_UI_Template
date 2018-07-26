package kawasaki.icm.com.tw.kawasaki_ui.fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.MemoInformationAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.custom.Tools;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs.EditDialogFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs.InformationDialogFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs.ListDialogFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs.SeekBarDialogFragment;
import kawasaki.icm.com.tw.kawasaki_ui.interfaces.IDialogItemListener;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.RadioButtonListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.MemoInformation;


/**
 * Created by icm_mobile on 2018/6/28.
 */

public class MemoFragment extends Fragment implements IRecyclerViewClickListener,IDialogItemListener {
    RecyclerView mRecyclerView;
    MemoInformationAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    Context context;
    List<MemoInformation> mData = new ArrayList<>();
    TextView tvFocus;
    Button btnSave;

    public static MemoFragment newInstance() {
        MemoFragment f = new MemoFragment();
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        Resources resources = context.getResources();
        int[] titles = resources.getIntArray(R.array.table_offline_edit_data);
//        for(int i = 0; i < 21; i++) {
        mData.add(new MemoInformation("Condition","Wet",""));
        mData.add(new MemoInformation("Weather","Cloudy",""));
        mData.add(new MemoInformation("Temperature","0","degC"));
        mData.add(new MemoInformation("Humidity","200","%"));
        mData.add(new MemoInformation("Atmospheric Pressure","130","kPa"));
        mData.add(new MemoInformation("Comment","write message here",""));


//        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        initModel();
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_memo_information,container,false);
        btnSave = (Button)v.findViewById(R.id.btn_save);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MemoInformationAdapter(mData , context ,this);
        mRecyclerView.setAdapter(mAdapter);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoAlertDialog(R.string.memo_information,"Richer Fuel Setting");
            }
        });
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

    private TextView getChildTextView(View v) {
        ViewGroup parent = (ViewGroup) v;
        ViewGroup frameLayout = (ViewGroup) parent.getChildAt(1);//只拿中間的FrameLayout
        TextView child = (TextView) frameLayout.getChildAt(0);//拿TextView(tv_Value)
        return child;
    }

    @Override
    public void recyclerViewItemClicked(View v, int position) {
        Log.d("Memo", " position : " + position + " clicked");
        List<String> data = new ArrayList<>();
        tvFocus = getChildTextView( v);
        int currentValue = 0;
        switch (position) {
            case 0 :
                data.add("Blank");
                data.add("Dry");
                data.add("Good");
                data.add("Wet");
                data.add("Muddy");
                data.add("Sand");
                data.add("Hard");
                showListAlertDialog(data,R.string.memo_condition);
                break;
            case 1 :
                data.add("Blank");
                data.add("Sunny");
                data.add("Cloudy");
                data.add("Rain");
                data.add("Snow");
                showListAlertDialog(data,R.string.memo_weather);
                break;
            case 2 :
                currentValue = Integer.parseInt(tvFocus.getText().toString());
                showSeekBarAlertDialog(-999,999,currentValue,R.string.memo_temperature,context.getResources().getString(R.string.memo_message_temperature));
                break;
            case 3 :
                currentValue = Integer.parseInt(tvFocus.getText().toString());
                showSeekBarAlertDialog(0,999,currentValue,R.string.memo_humidity,context.getResources().getString(R.string.memo_message_humidity));
                break;
            case 4 :
                currentValue = Integer.parseInt(tvFocus.getText().toString());
                showSeekBarAlertDialog(0,999,currentValue,R.string.memo_atmospheric,context.getResources().getString(R.string.memo_message_atmospheric));
                break;
            case 5 :
                showEditAlertDialog(R.string.memo_comment,tvFocus.getText().toString());
                break;
        }
    }
    private void showListAlertDialog(List<String > data,int title){
        // Create an instance of the dialog fragment and show it
        ListDialogFragment dialog = new ListDialogFragment();
        dialog.setIDialogItemListener(this);
        dialog.setData(data);
        dialog.setTitle(title);
        dialog.show(MainActivity.Instance.getSupportFragmentManager(), "ListDialogFragment");

    }

    private void showSeekBarAlertDialog(int min,int max,int defaultVal,int title,String msg){
        SeekBarDialogFragment dialog = new SeekBarDialogFragment();
        dialog.setIDialogItemListener(this);
        dialog.setDefaultValue(defaultVal);
        dialog.setMin(min); //min需先設定，否則max會出錯
        dialog.setMax(max);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show(MainActivity.Instance.getSupportFragmentManager(), "SeekBarDialogFragment");

    }

    private void showEditAlertDialog(int title,String content){
        EditDialogFragment dialog = new EditDialogFragment();
        dialog.setIDialogItemListener(this);
        dialog.setTitle(title);
        dialog.setData(content);
        dialog.show(MainActivity.Instance.getSupportFragmentManager(), "EditDialogFragment");
    }

    private void showInfoAlertDialog(int title,String content){
        InformationDialogFragment dialog = new InformationDialogFragment();
        dialog.setIDialogItemListener(this);
        dialog.setTitle(title);
        dialog.setData(content);
        dialog.show(MainActivity.Instance.getSupportFragmentManager(), "InformationDialogFragment");
    }

    private void showRadioButtonDialog(List<String> data){
        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog_condition);
        dialog.getWindow().getAttributes().windowAnimations = R.anim.fade_in;
        //TODO dialog 出現的動畫未完成
        dialog.setTitle("fsdfsdf");
        RadioGroup rg = dialog.findViewById(R.id.radio_group);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1f);
        params.weight = 1.0f;
        params.height = Tools.getScreenHighPixel((MainActivity) context) / 9;

        for(int i = 0; i < data.size(); i++){
            RadioButton rb = new RadioButton(context);
            rb.setLayoutParams(params);
            rb.setText(data.get(i));
            rb.setTextSize(20);
            rb.setGravity(Gravity.CENTER);
            rb.setOnClickListener(new RadioButtonListener(dialog,this));
            rg.addView(rb);
        }
        dialog.show();

    }

    @Override
    public void setTextViewValue(String t) {
        if(tvFocus != null)
            tvFocus.setText(t);
    }
}


    //尚未完成
//    private TextView getChildTextViewRecursively(ViewGroup root) {
//        final int childCount = root.getChildCount();
//        TextView tv = null;
//        if (childCount > 1) {
//            getChildTextViewRecursively((ViewGroup) root.getChildAt(1));
//        }
//        else {
////            tv = (TextView) root.getChildAt(0);
//            return (TextView) root.getChildAt(0);
//        }
//
//        return tv;
//    }