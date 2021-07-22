package dev.chau.adsmodjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import java.util.ArrayList;

import dev.chau.adsmodjava.Utils.CustomDialogFragment;
import dev.chau.adsmodjava.Utils.MyTestService;
import dev.chau.adsmodjava.Utils.PopupManager;
import dev.chau.adsmodjava.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    
    private ActivitySplashBinding binding;
    CustomDialogFragment dialogOneFunc, dialogTwoFunc;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // location-related task you need to do.
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    initService();
                }

            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
            }
            return;
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnClickService.setOnClickListener(v -> {
            Intent intent = new Intent(this, ServiceActivity.class);
            startActivity(intent);
        });

        initGooglegmap();

        initCustomDialog();

        initShowAds();

        initShowPopup();
        
        initShowPopupMenu();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //Location Permission already granted
            initService();
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, MyTestService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(this, MyTestService.class));
    }

    private void initService() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        double latitude = intent.getDoubleExtra(MyTestService.EXTRA_LATITUDE, 0);
                        double longitude = intent.getDoubleExtra(MyTestService.EXTRA_LONGITUDE, 0);
                        String s = "Lat: " + latitude + ", Lng: " + longitude;
                        binding.textSplash.setText(s);
                    }
                }, new IntentFilter(MyTestService.ACTION_LOCATION_BROADCAST)
        );
    }

    private void initShowPopupMenu() {

        ArrayList<String> list = new ArrayList<>();

        list.add("Chau 1");
        list.add("Chau 2");
        list.add("Chau 3");
        list.add("Chau 4");

        binding.btnClickShowPopupMenu.setOnClickListener(v -> {
            PopupManager.ShowPopUpMenu(this, v, list, inputText -> {
                Toast.makeText(this, "ShowPopupMenu "+inputText, Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void initShowPopup() {
        binding.btnClickShowPopup.setOnClickListener(v -> {
            PopupManager.ShowPopupWindow(this, inputText -> {
                Toast.makeText(this, "clickShowPopup "+inputText, Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void initShowAds() {
        binding.btnClickShowAds.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initCustomDialog() {
        dialogOneFunc = CustomDialogFragment.newInstance("dialogOneFunc", false, inputText -> {
            Toast.makeText(this, "dialogOneFunc "+inputText, Toast.LENGTH_SHORT).show();
        });
        dialogTwoFunc = CustomDialogFragment.newInstance("dialogTwoFunc", true, inputText -> {
            Toast.makeText(this, "dialogTwoFunc "+inputText, Toast.LENGTH_SHORT).show();
        });

        FragmentManager fm = getSupportFragmentManager();

        binding.btnClickOneFunc.setOnClickListener(v -> {
            dialogOneFunc.show(fm, CustomDialogFragment.TAG);
        });

        binding.btnClickTwoFunc.setOnClickListener(v -> {
            dialogTwoFunc.show(fm, CustomDialogFragment.TAG);
        });
    }

    private void initGooglegmap() {
        binding.btnClickGoogleMap.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });
    }
}