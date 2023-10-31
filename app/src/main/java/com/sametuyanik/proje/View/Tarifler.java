package com.sametuyanik.proje.View;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sametuyanik.proje.Model.FoodModel;
import com.sametuyanik.proje.R;
import com.sametuyanik.proje.databinding.ActivityTariflerBinding;

public class Tarifler extends AppCompatActivity {

    ActivityTariflerBinding binding;
    TextView textViewTarif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTariflerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle b = getIntent().getExtras();
        FoodModel m = (FoodModel) b.get("tarif");

        textViewTarif = findViewById(R.id.textViewTarif);


        textViewTarif = new TextView(this);
        textViewTarif.setText(m.getSummary());

        binding.getRoot().addView(textViewTarif);





        /*TextView t = new TextView(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t.setText(Html.fromHtml(m.getSummary(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            t.setText(Html.fromHtml(m.getSummary()));
        }
        t.setText(m.getSummary());

        t.setTextColor(Color.parseColor("#000000"));
        binding.getRoot().addView(t);
        /*
         */

    }
}