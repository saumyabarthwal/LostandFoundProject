package com.example.project2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Home screen opens first
        loadFragment(new HomeFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_home) {
                loadFragment(new HomeFragment());
                return true;
            }

            if (id == R.id.nav_add) {
                loadFragment(new AddItemFragment());
                return true;
            }

            if (id == R.id.nav_my_items) {
                loadFragment(new MyItemsFragment());
                return true;
            }

            if (id == R.id.nav_settings) {
                loadFragment(new SettingsFragment());
                return true;
            }

            return false;
        });
    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}