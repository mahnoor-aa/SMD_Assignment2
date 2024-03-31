package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRestaurants;
    RestaurantAdapter adapter;
    ArrayList<RestaurantInfo> list;
    Button btnAddRestaurant;
    SharedPreferences spref;
    Spinner spfilter;
    EditText etfilter;
    Button btnfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadRestaurant();
        setupView();

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        btnfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String option = spfilter.getSelectedItem().toString();
                String text = etfilter.getText().toString().toLowerCase();

                ArrayList<RestaurantInfo> listfilter = new ArrayList<>();
                if (text.isEmpty()) {
                    listfilter.addAll(list);
                } else {
                    for (RestaurantInfo res : list) {
                        if (Objects.equals(option, "Filter by Name")) {
                            if (res.getName().toLowerCase().contains(text)) {
                                listfilter.add(res);
                            }
                        } else if (Objects.equals(option, "Filter by Location")) {
                            if (res.getLocation().toLowerCase().contains(text)) {
                                listfilter.add(res);
                            }
                        } else if (Objects.equals(option, "Filter by Phone")) {
                            if (res.getPhone().toLowerCase().contains(text)) {
                                listfilter.add(res);
                            }
                        } else if (Objects.equals(option, "Filter by Rating")) {
                            if (res.getRating().toLowerCase().contains(text)) {
                                listfilter.add(res);
                            }
                        } else if (Objects.equals(option, "Filter by Description")) {
                            if (res.getDesc().toLowerCase().contains(text)) {
                                listfilter.add(res);
                            }
                        }
                    }
                }

                adapter.setRestaurant(listfilter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void init() {
        list = new ArrayList<>();
        rvRestaurants = findViewById(R.id.rvRestaurants);
        btnAddRestaurant = findViewById(R.id.btnAddNewRestaurant);
        spref = getSharedPreferences("Restaurant", MODE_PRIVATE);
        spfilter=findViewById(R.id.spFilter);
        etfilter=findViewById(R.id.etFilter);
        btnfilter=findViewById(R.id.btnFilter);
    }

    protected void onResume() {
        super.onResume();
        loadRestaurant();
        adapter.setRestaurant(list);
        adapter.notifyDataSetChanged();
    }

    private void loadRestaurant() {
        Gson gson = new Gson();
        String json = spref.getString("Restaurant", null);
        if (json != null && !json.isEmpty()) {
            RestaurantInfo[] restaurants = gson.fromJson(json, RestaurantInfo[].class);
            list = new ArrayList<>(Arrays.asList(restaurants));
        } else {
            list = new ArrayList<>();
        }
    }

    private void setupView() {
        rvRestaurants.setHasFixedSize(true);
        adapter = new RestaurantAdapter(list);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurants.setAdapter(adapter);
    }
}
