package com.example.ebookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ebookapp.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityClickHandler clickHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        clickHandler=new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);


        binding.spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");

        binding.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

            }
        });



    }

    public class MainActivityClickHandler {
        Context mCtx;

        public MainActivityClickHandler(Context mCtx) {
            this.mCtx = mCtx;
        }
        public void onFABclicked(View view){
            Intent intent=new Intent(MainActivity.this,AddBook.class);
            startActivity(intent);
        }

    }

}
