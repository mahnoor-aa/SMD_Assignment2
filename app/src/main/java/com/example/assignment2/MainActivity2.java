package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    EditText name;
    EditText location;
    EditText phone;
    EditText rating;
    EditText desc;

    Button btnsubmit;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidForm()) {
                    String savename = name.getText().toString();
                    String savelocation = location.getText().toString();
                    String savephone = phone.getText().toString();
                    String saveratings = rating.getText().toString();
                    String saveinfo = desc.getText().toString();

                    RestaurantInfo res = new RestaurantInfo(savename, savelocation, savephone, saveratings, saveinfo);
                    saveRestaurant(res);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity2.this,"Fill in all the Fields!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean ValidForm()
    {
        return !isEmpty(name) && !isEmpty(location)&& !isEmpty(phone) && !isEmpty(rating) && !isEmpty(desc);
    }

    private boolean isEmpty(EditText text){

        return text.getText().toString().trim().length()==0;
    }

    private void saveRestaurant(RestaurantInfo res)
    {
        SharedPreferences spref = getSharedPreferences("Restaurant", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = spref.getString("Restaurant", null);
        ArrayList<RestaurantInfo> list;

        if (json!=null && !json.isEmpty()) {
            RestaurantInfo[] restaurants = gson.fromJson(json, RestaurantInfo[].class);
            list= new ArrayList<>(Arrays.asList(restaurants));
        }
        else{
            list= new ArrayList<>();
        }

        list.add(res);
        SharedPreferences.Editor edit=spref.edit();
        json=gson.toJson(list);
        edit.putString("Restaurant",json);
        edit.apply();
    }


    public void init(){
        name=findViewById(R.id.etname);
        location=findViewById(R.id.etlocation);
        phone=findViewById(R.id.etphone);
        rating=findViewById(R.id.etrating);
        desc=findViewById(R.id.etdesc);
        btnsubmit=findViewById(R.id.btnsubmit);
        btnback=findViewById(R.id.btnback);
    }
}