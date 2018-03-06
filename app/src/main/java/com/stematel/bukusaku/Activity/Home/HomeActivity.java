package com.stematel.bukusaku.Activity.Home;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.stematel.bukusaku.Activity.Home.Fragment.Home.HomeFragment;
import com.stematel.bukusaku.Activity.Home.Fragment.LaporFragment;
import com.stematel.bukusaku.Activity.Home.Fragment.NotifFragment;
import com.stematel.bukusaku.Activity.Home.Fragment.Pasal.PasalActivityFragment;
import com.stematel.bukusaku.Activity.Home.Fragment.Pasal.PasalFragment;
import com.stematel.bukusaku.Activity.Login.LoginActivity;
import com.stematel.bukusaku.Activity.Settings.SettingActivity;
import com.stematel.bukusaku.Adapter.Peraturan.PeraturanAdapter;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;
import com.stematel.bukusaku.Utils.PaginationAdapterCallback;

public class HomeActivity extends AppCompatActivity implements PaginationAdapterCallback {

    PeraturanAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHawk();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar);
        bottomBar.setItems(R.xml.tab_menu);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home){
                    commitFragment(new HomeFragment());
                } else if (tabId == R.id.tab_peraturan){
                    commitFragment(new PasalActivityFragment());
                } else if (tabId == R.id.tab_lapor){
                    commitFragment(new LaporFragment());
                } else {
                    commitFragment(new NotifFragment());
                }
            }
        });
    }

    private void initHawk() {
        Hawk.init(this).setLogInterceptor(new LogInterceptor() {
            @Override
            public void onLog(String message) {
                Log.d("Hawk", message);
            }
        }).build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_option_menu, menu);

        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.Settings){
            LocalService.delLogin();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

        return true;
    }

    private void commitFragment(Fragment fragment){

        if (getSupportFragmentManager().findFragmentById(R.id.contentContainer)!= null){
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.contentContainer)).commit();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer, fragment).commit();

        /*android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer, fragment);
        fragmentTransaction.commit();*/
    }

    @Override
    public void retryPageLoad() {

    }
}
