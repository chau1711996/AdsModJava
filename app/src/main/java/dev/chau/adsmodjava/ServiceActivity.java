package dev.chau.adsmodjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import dev.chau.adsmodjava.Utils.MyService;
import dev.chau.adsmodjava.databinding.ActivityServiceBinding;

public class ServiceActivity extends AppCompatActivity {

    private ActivityServiceBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent = new Intent(this, MyService.class);

        binding.btnStartService.setOnClickListener(v -> {
            startService(intent);
        });

        binding.btnStopService.setOnClickListener(v -> {
            stopService(intent);
        });
    }
}