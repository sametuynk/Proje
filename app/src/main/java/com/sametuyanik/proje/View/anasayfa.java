package com.sametuyanik.proje.View;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sametuyanik.proje.Fragment.FavoriteFragment;
import com.sametuyanik.proje.Fragment.HomeFragment;
import com.sametuyanik.proje.Fragment.SearchFragment;
import com.sametuyanik.proje.Fragment.ShareFragment;
import com.sametuyanik.proje.R;
import com.sametuyanik.proje.databinding.ActivityAnasayfaBinding;

public class anasayfa extends AppCompatActivity {

    ActivityAnasayfaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnasayfaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setBackground(null);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new HomeFragment()).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.paylas:
                    replaceFragment(new ShareFragment());
                    break;
                case R.id.favoriler:
                    replaceFragment(new FavoriteFragment());
                    break;
                case R.id.anasayfa:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.search_bar:
                    replaceFragment(new SearchFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}