package kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.interfaces.IDialogItemListener;

/**
 * Created by icm_mobile on 2018/7/25.
 */

public class EditDialogFragment extends DialogFragment {
    String mContent;
    int title;
    EditText etContent;
    Button btnSave;
    IDialogItemListener mItemListener;

    public static EditDialogFragment newInstance () {
        EditDialogFragment f = new EditDialogFragment();
        return f;
    }

    public void setIDialogItemListener(IDialogItemListener listener) {
        this.mItemListener = listener;
    }

    public void setData(String data) {
        this.mContent = data;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_memo_edit, null);
        etContent = (EditText)v.findViewById(R.id.et_Content);
        btnSave = (Button)v.findViewById(R.id.btnSave);
        etContent.setText(mContent);
        etContent.setSelection(etContent.getText().length());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItemListener.setTextViewValue(etContent.getText().toString());
                //TODO: 將檔案寫入硬體
                dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
               .setView(v);


        return builder.create();
    }
}
