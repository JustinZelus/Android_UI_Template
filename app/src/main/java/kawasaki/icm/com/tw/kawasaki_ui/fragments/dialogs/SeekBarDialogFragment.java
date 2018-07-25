package kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.interfaces.IDialogItemListener;

/**
 * Created by icm_mobile on 2018/7/25.
 */

public class SeekBarDialogFragment extends DialogFragment {

    IDialogItemListener mItemListener;
    int step = 1;
    int max;
    int min = 0;
    int title;
    int currentVal;
    String mMessage;
    TextView tvVal;
    TextView tvMessage;
    SeekBar seekBar;
    Button btnAdd;
    Button btnMinus;
    Handler seekBarHandler;

    public static SeekBarDialogFragment newInstance () {
        SeekBarDialogFragment f = new SeekBarDialogFragment();
        return f;
    }

    public void setIDialogItemListener(IDialogItemListener listener) {
        this.mItemListener = listener;
    }

    public void setMin(int min) {
        this.min= min;
    }

    public void setMax(int max) {

        this.max = min == 0 ? max : max * 2;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public void setDefaultValue(int val) {
        this.currentVal = val;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seekBarHandler = new Handler();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_memo_seekbar, null);
        tvMessage = (TextView)v.findViewById(R.id.tv_Message);
        tvVal     = (TextView)v.findViewById(R.id.tv_Val);
        seekBar   = (SeekBar)v.findViewById(R.id.seekBar);
        btnMinus  = (Button)v.findViewById(R.id.btn_minus);
        btnAdd    = (Button)v.findViewById(R.id.btn_Add);
        tvMessage.setText(mMessage);
        tvVal.setText(String.valueOf(currentVal));
        seekBar.setMax(max);
        if(currentVal >= 0)
            seekBar.setProgress(min == 0 ? currentVal : max - currentVal );
        else
            seekBar.setProgress( max + currentVal);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                int value = min + (progress * step);
                int value = max - (progress * step);
//                currentVal = value;
                tvVal.setText(String.valueOf(value));
                Log.d("seekBarValue","" + value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seekBarHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(seekBar != null) {
//                            int val = currentVal >= 0 ? max - currentVal : max + currentVal;
                            int val = Integer.parseInt(tvVal.getText().toString()) + 1;
                            seekBar.setProgress(convertToProgressVal(val));
                            tvVal.setText(String.valueOf(val));
                        }
                    }
                });
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBarHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(seekBar != null)
                            seekBar.setProgress(++currentVal);
                    }
                });
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
               .setView(v)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mItemListener.setTextViewValue(tvVal.getText().toString());
                        //TODO: 將數值寫入硬體
                }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

    private int convertToProgressVal(int val) {
        return val >=0 ? max - val : max + val;
    }
}
