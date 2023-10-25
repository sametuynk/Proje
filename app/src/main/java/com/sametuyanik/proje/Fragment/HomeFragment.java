package com.sametuyanik.proje.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sametuyanik.proje.Adapter.RecyclerViewAdapter;
import com.sametuyanik.proje.Model.FoodModel;
import com.sametuyanik.proje.Model.Receipts;
import com.sametuyanik.proje.Service.FoodAPI;
import com.sametuyanik.proje.Service.RecyclerViewInterface;
import com.sametuyanik.proje.View.Tarifler;
import com.sametuyanik.proje.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<FoodModel> foodModels;
    private String BASE_URL = "https://api.spoonacular.com/";
    Retrofit retrofit;
    RecyclerViewAdapter recyclerViewAdapter;
    FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();
    }

    private void loadData() {
        FoodAPI foodAPI = retrofit.create(FoodAPI.class);
        Call<Receipts> call = foodAPI.getProducts("20", "27ce4019daf44998a26116bfa08d8dc2");
        call.enqueue(new Callback<Receipts>() {
            @Override
            public void onResponse(Call<Receipts> call, Response<Receipts> response) {
                if (response.isSuccessful()) {
                    Receipts responseList = response.body();
                    foodModels = new ArrayList<>(response.body().receipts);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerViewAdapter = new RecyclerViewAdapter(HomeFragment.this::onItemClick, foodModels, getContext());
                    binding.recyclerView.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onFailure(Call<Receipts> call, Throwable t) {
                Toast.makeText(getContext(), "Hata", Toast.LENGTH_SHORT).show();
                Log.d("HATA", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), Tarifler.class);
        intent.putExtra("tarif", foodModels.get(position));
        startActivity(intent);
    }
}



