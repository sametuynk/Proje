package com.sametuyanik.proje.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sametuyanik.proje.Adapter.RecyclerViewAdapter;
import com.sametuyanik.proje.Model.FoodModel;
import com.sametuyanik.proje.Model.Receipts;
import com.sametuyanik.proje.R;
import com.sametuyanik.proje.Service.FoodAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class anasayfa extends AppCompatActivity {

    ArrayList<FoodModel> foodModels;
    private String BASE_URL = "https://api.spoonacular.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);





        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();

    }


    private void loadData() {

        FoodAPI foodAPI = retrofit.create(FoodAPI.class);

        Call<Receipts> call = foodAPI.getProducts("100", "27ce4019daf44998a26116bfa08d8dc2");

        call.enqueue(new Callback<Receipts>() {
            @Override
            public void onResponse(Call<Receipts> call, Response<Receipts> response) {
                if (response.isSuccessful()) {
                    Receipts responseList = response.body();
                    foodModels = new ArrayList<>(response.body().receipts);

                    //Recyclerview
                    recyclerView.setLayoutManager(new LinearLayoutManager(anasayfa.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(foodModels, anasayfa.this);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Receipts> call, Throwable t) {
                Toast.makeText(anasayfa.this, "Hata", Toast.LENGTH_SHORT).show();
                Log.d("HATA", "onFailure: " + t.getMessage());
            }
        });
    }
}