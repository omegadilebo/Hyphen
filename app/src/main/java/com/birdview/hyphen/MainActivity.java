package com.birdview.hyphen;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.birdview.hyphen.R;
import com.birdview.hyphen.adapters.BottomNavPagerAdapter;
import com.birdview.hyphen.fragments.DashboardChildFragment;
import com.birdview.hyphen.fragments.DashboardFragment;
import com.birdview.hyphen.fragments.HomeFragment;
import com.birdview.hyphen.fragments.NotificationFragment;
import com.birdview.hyphen.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.mapboxsdk.Mapbox;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            toolbar.setTitle(item.getTitle());
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_home:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.snack_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_snack_bar:
                showSnackBar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }*/

/*    private void showSnackBar() {
        Snackbar.make(navigation, "Some text", Snackbar.LENGTH_LONG).show();

    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mapbox.getInstance(this,getString(R.string.access_token));

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager_main);
        BottomNavPagerAdapter adapter = new BottomNavPagerAdapter(getSupportFragmentManager(),0);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        initTitle();
    }

    private void initTitle() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(navigation.getMenu().getItem(0).getTitle());
            }
        });
    }
}
