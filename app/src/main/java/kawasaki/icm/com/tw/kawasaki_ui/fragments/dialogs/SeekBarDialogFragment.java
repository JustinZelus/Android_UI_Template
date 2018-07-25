package kawasaki.icm.com.tw.kawasaki_ui.fragments.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.interfaces.IDialogItemListener;

/**
 * Created by icm_mobile on 2018/7/25.
 */

public class ListDialogFragment extends DialogFragment {

//    List mOfficeListItems;
    ListView listView;
    IDialogItemListener mItemListener;
    List<String> mDatas;

    public static ListDialogFragment newInstance () {
        ListDialogFragment f = new ListDialogFragment();
        return f;
    }

    public void setIDialogItemListener(IDialogItemListener listener) {
        this.mItemListener = listener;
    }

    public void setDatas(List<String> data) {
        this.mDatas = data;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_memo_list, null);
        listView = (ListView)v.findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1,mDatas));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                if(mItemListener != null)
                    mItemListener.setTextViewValue(tv.getText().toString());
                dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.memo_condition)
               .setView(v)

//                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // FIRE ZE MISSILES!
//                    }
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                })
                ;


        return builder.create();
    }
}
