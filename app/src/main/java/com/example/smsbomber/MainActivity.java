package com.example.smsbomber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.example.smsbomber.adapters.TabLayoutAdapter;
import com.example.smsbomber.services.SMSService;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_READ_CONTACTS = 0;
    public static final int REQUEST_RECEIVE_SMS = 1;
    public static final int REQUEST_SEND_SMS = 2;
    public static final int REQUEST_READ_SMS = 3;
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



        if(
            (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
            && (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED)
        ) {
            Intent i = new Intent(this, SMSService.class);
            i.putExtra("Key", "Value for the service");
            this.startService(i);
        } else {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.RECEIVE_SMS },REQUEST_RECEIVE_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantRes) {
        super.onRequestPermissionsResult(requestCode, permissions, grantRes);

        switch (requestCode) {
            case REQUEST_READ_CONTACTS: {
                if (grantRes.length > 0 && grantRes[0] == PackageManager.PERMISSION_GRANTED) {// Permission granted
                    Log.d("Permissions", "Contact granted permission");
                } else {// Permission denied
                }
                return;
            }

            case REQUEST_RECEIVE_SMS: {
                if (grantRes.length > 0 && grantRes[0] == PackageManager.PERMISSION_GRANTED) {// Permission granted
                    Log.d("Permissions", "Receive sms granted permission");

                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Intent i = new Intent(this, SMSService.class);
                        i.putExtra("Key", "Value for the service");
                        this.startService(i);
                    } else {
                        ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.SEND_SMS }, REQUEST_SEND_SMS);
                    }

                } else {// Permission denied
                }
                return;
            }

            case REQUEST_SEND_SMS: {
                if (grantRes.length > 0 && grantRes[0] == PackageManager.PERMISSION_GRANTED) {// Permission granted
                    Log.d("Permissions", "Send sms granted permission");
                } else {// Permission denied
                }
                return;
            }

            case REQUEST_READ_SMS: {
                if (grantRes.length > 0 && grantRes[0] == PackageManager.PERMISSION_GRANTED) {// Permission granted
                    Log.d("Permissions", "Read sms granted permission");
                } else {// Permission denied
                }
                return;
            }
        }
    }
}