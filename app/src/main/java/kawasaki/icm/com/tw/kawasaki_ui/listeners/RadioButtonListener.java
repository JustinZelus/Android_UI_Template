package kawasaki.icm.com.tw.kawasaki_ui.listeners;

import android.app.Dialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import kawasaki.icm.com.tw.kawasaki_ui.interfaces.IDialogItemListener;

/**
 * Created by icm_mobile on 2018/7/12.
 */

public class RadioButtonListener implements View.OnClickListener {
    Dialog dialog;
    IDialogItemListener iDialougRadioListener;

    public RadioButtonListener(Dialog dialog,IDialogItemListener iDialougRadioListener) {
        this.dialog = dialog;
        this.iDialougRadioListener = iDialougRadioListener;
    }

    @Override
    public void onClick(View v) {
        RadioButton r = (RadioButton)v;
        Log.d("RadioButton"," " + r.getText());
        iDialougRadioListener.setTextViewValue(r.getText().toString());

        final Handler handler  = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 500);
    }
}
