package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.futurebankgrupo1.databinding.ActivityRecargaCelularBinding;

public class RecargaCelularActivity extends AppCompatActivity {

    private ActivityRecargaCelularBinding binding;
    private MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaCelularBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);



//        binding.button.setOnClickListener(v -> {
//            new String(String.valueOf(viewModel.comprarCartaoCredito()));
//        });

        //binding.button.setOnClickListener(String.valueOf(viewModel.comprarCartaoCredito()));


    }
}