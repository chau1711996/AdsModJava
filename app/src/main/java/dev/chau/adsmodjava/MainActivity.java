package dev.chau.adsmodjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import dev.chau.adsmodjava.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LogMainActivity";

    ViewPager2 viewPager;
    TabLayout tabLayout;
    AdView mAdView;
    AdsManager adsManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);

        initViewPager();
        initAdsMod();
    }

    private void initAdsMod(){
        adsManager = AdsManager.getInstance(this, TAG);
        adsManager.loadBanner(mAdView);
        adsManager.setInterstitial("ca-app-pub-3940256099942544/1033173712");
    }

    private void initViewPager(){
        ArrayList<String> titles = new ArrayList();
        titles.add("Vertical");
        titles.add("Grid");

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int dem = 0;
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                dem++;
                if(dem == 3){
                    adsManager.showInterstitial(MainActivity.this);
                    dem = 0;
                }
            }
        });

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) ->
                        tab.setText(titles.get(position))
        ).attach();
    }
}