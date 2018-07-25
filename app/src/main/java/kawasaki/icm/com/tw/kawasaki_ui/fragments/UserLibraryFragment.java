package kawasaki.icm.com.tw.kawasaki_ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.MainActivity;
import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.R;
import kawasaki.icm.com.tw.kawasaki_ui.adapter.DirectoryAdapter;
import kawasaki.icm.com.tw.kawasaki_ui.custom.SwipeController;
import kawasaki.icm.com.tw.kawasaki_ui.custom.SwipeControllerActions;
import kawasaki.icm.com.tw.kawasaki_ui.enums.AppAttribute;
import kawasaki.icm.com.tw.kawasaki_ui.listeners.IRecyclerViewClickListener;
import kawasaki.icm.com.tw.kawasaki_ui.model.Directory;


/**
 * Created by icm_mobile on 2018/6/22.
 */

public class UserLibraryFragment extends Fragment implements IRecyclerViewClickListener {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<Directory> mData = new ArrayList<>();
    Context context;
    private boolean isAddItemDivider = true;
    int myPage = -1;

    public static UserLibraryFragment newInstance(int page){
        UserLibraryFragment f = new UserLibraryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PAGE",page);
        f.setArguments(bundle);
        return f;
    }

    @SuppressLint("ResourceType")
    public void initModel() {
        myPage = getArguments().getInt("PAGE");
        Resources resources = context.getResources();
        String[] titles = resources.getStringArray(R.array.user_library_directory_name);
        TypedArray backgrounds = resources.obtainTypedArray(R.array.directoryRipples);
        for(int i = 0; i < titles.length; i++) {
            mData.add(new Directory( titles[i] , backgrounds.getDrawable(i % 2)));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        initModel();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_library,container,false);
        mRecyclerView =  v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false); //版面設置為縱向
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DirectoryAdapter(mData,(MainActivity)getContext(),this);
        mRecyclerView.setAdapter(mAdapter);
        if(isAddItemDivider)
            mRecyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        /**滑動刪除功能*/
        final SwipeController swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                ((DirectoryAdapter) mAdapter).removeItem(position);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });

        /**action bar 標題更新*/
        if(AppAttribute.IS_UPDATE_TOOLBAR_TITLE)
            MainActivity.Instance.updateToolbar(Pages.DIRECTORY_USER_LIBRARY);
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
        Fragment des = MapAdjustFragment.newInstance(myPage);
        if(des != null)
            MainActivity.Instance.switchFragment(this,des);
    }
}
