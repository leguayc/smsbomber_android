package com.example.smsbomber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.smsbomber.adapters.TabLayoutAdapter;
import com.example.smsbomber.services.SMSService;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_READ_CONTACTS = 0;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabLayoutAdapter adapter = new TabLayoutAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*Intent i = new Intent(this, SMSService.class);
        // Optionnel (seulement pour passer des arguments)
        i.putExtra("Key", "Value for the service");
        this.startService(i);*/

        requestPermission();
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
            // show UI part if you want here to show some rationale !!!
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantRes) {
        super.onRequestPermissionsResult(requestCode, permissions, grantRes);

        switch (requestCode) {
            case REQUEST_READ_CONTACTS: {
                if (grantRes.length > 0 && grantRes[0] == PackageManager.PERMISSION_GRANTED) {// Permission granted
                } else {// Permission denied
                }
                return;
            }
        }
    }
}