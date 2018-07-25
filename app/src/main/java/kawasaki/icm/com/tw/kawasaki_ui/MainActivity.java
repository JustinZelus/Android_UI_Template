package kawasaki.icm.com.tw.kawasaki_ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import kawasaki.icm.com.tw.kawasaki_ui.enums.Pages;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.MapAdjustFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.OpeningFragment;
import kawasaki.icm.com.tw.kawasaki_ui.fragments.offline.MemoFragment_OFFLine;


public class MainActivity extends AppCompatActivity {

    public static MainActivity Instance;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    /** 畫面上方是否加入toolbar */
    boolean isAddToolbar = false;
    /** 是否在toolbar中加入menu */
    boolean isAddMenuToToolbar = false;
    /** 是否加入抽屜*/
    boolean isAddDrawer = false;

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Instance = this;

        if(isAddToolbar ) {
//            toolbar = findViewById(R.id.toolbar); 若沒有要加toolbar，此行先註解(對應xml也註解)
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            ActionBar actionBar = getSupportActionBar();
            //        actionBar.setDisplayHomeAsUpEnabled(true);
            //        actionBar.setHomeAsUpIndicator(R.drawable.icon_hamburger);
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        String dp = "";
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                dp = "DENSITY_LOW";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                dp = "DENSITY_MEDIUM";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                dp = "DENSITY_HIGH";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                dp = "DENSITY_XHIGH";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                dp = "DENSITY_XXHIGH";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                dp = "DENSITY_XXXHIGH";
                break;

        }
        Log.d("density qualifiers","" + dp);

        if(isAddDrawer) {
            mDrawerLayout = findViewById(R.id.drawer_layout);
            navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
        }

//        switchFragment(MapAdjustFragment.newInstance(Pages.OFF_LINE));
        switchFragment(MemoFragment_OFFLine.newInstance());
//        Log.i("*** Elenasys :: ", "StatusBar Height= " + getStatusBarHeight() + " , TitleBar Height = " + titleBarHeight);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isAddMenuToToolbar)
            getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Log.d("requestCone:" ,"" + requestCode);
        Log.d("resultCode:",""+resultCode);;
//        if (requestCode == ) {
            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                // Do something with the contact here (bigger example below)
//            }
//        }
    }

    public void switchFragment(Fragment destFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(!destFragment.isAdded())
            fragmentTransaction.add(R.id.fragment_container,destFragment);
//        else
//            fragmentTransaction.replace(R.id.fragment_container,destFragment);
        fragmentTransaction.commit();
    }

    boolean needToAddBackStack = true;
    boolean needAnimation = true;

    public void switchFragment(Fragment from , Fragment to) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(needAnimation)
            fragmentTransaction.setCustomAnimations(R.anim.fade_in,R.anim.fade_out); //必須用自定義動畫才有效果，原因https://www.e-learn.cn/content/wangluowenzhang/89903
        if(from.isAdded())
            fragmentTransaction.replace(R.id.fragment_container,to);
        //將當前fragment加入stack，讓下一個頁面可退回上一頁。若沒加，則當前fragment會被銷毀。
        if(needToAddBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void setToolbarTitle(int name) {
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updateToolbar(String page) {
        int title = -1;

        switch (page) {
            case Pages.MENU_3_BUTTON:
                title = R.string.title_menu_3_button;
                break;
            case Pages.MENU_2_BUTTON:
                title = R.string.title_menu_2_button;
                break;
            case Pages.DIRECTORY_KXF:
                title = R.string.title_directory_kxf;
                break;
            case Pages.DIRECTORY_SAMPLE:
                title = R.string.title_directory_sample;
                break;
            case Pages.DIRECTORY_USER_LIBRARY:
                title = R.string.title_directory_user_library;
                break;

            default:
                title = R.string.app_name;
                break;
        }
        getSupportActionBar().setTitle(title);
        if(!page.equals(Pages.MENU_3_BUTTON))
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }








    public List<String> getDirectory_Names_KXF() {
        List<String> data = new ArrayList<>();
        if(data.size() <= 0) {
            data.add("\'09~\'11 KX450F");
            data.add("\'11~\'12 KX250F");
            data.add("\'12 KX450F");
            data.add("\'13 KX250F");
            data.add("\'13~\'15 KX450F");
            data.add("\'14 KX250F");
            data.add("\'16 KX450F");
            data.add("aaaaa");
            data.add("aaaaa");
            data.add("aaaaa");
            data.add("aaaaa");
        }
        return data;
    }

    public List<String> getDirectory_Names_Sample() {
        List<String> data = new ArrayList<>();
        if(data.size() <= 0) {
            data.add("Advanced Ignition Setting");
            data.add("Beginner");
            data.add("Hard Riding Surface");
            data.add("Leaner Fuel Setting");
            data.add("Retarded Ignition Setting");
            data.add("Richer Fuel Setting");
            data.add("Soft Riding Surface");
        }
        return data;
    }

    public List<String> getDirectory_Names_User_Library() {
        List<String> data = new ArrayList<>();
        if(data.size() <= 0) {
            data.add("Default");
        }
        return data;
    }
}
