package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.databinding.ActivityAplicarCpBinding;
import com.example.futurebankgrupo1.databinding.ActivityResgatarCcBinding;

public class ResgatarCC extends AppCompatActivity {
    ActivityResgatarCcBinding binding;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResgatarCcBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        binding.buttonResgatar.setOnClickListener(v -> {
            float valor = Float.parseFloat(binding.edtValorResgatar.getText().toString());
            float saldoCc = viewModel.exibirSaldoContaCorrente();
            float saldoCp = viewModel.exibibirSaldoPoupancaFirebase();

            if (viewModel.exibirSaldoContaCorrente() >=valor){
                viewModel.setarSaldo(saldoCc + valor);
                viewModel.setarSaldoPoupanca(saldoCp - valor);

//                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("chaveValorPix", binding.edtValorAplicar.getText().toString());
//                editor.commit();
//                Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosPix.class);
//                startActivity(intent);

            }else {
                Toast.makeText(this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }


        });


    }
}