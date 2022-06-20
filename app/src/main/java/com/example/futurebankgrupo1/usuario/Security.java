package com.example.futurebankgrupo1.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.configuracoes.TelaConfiguracoesActivity;
import com.example.futurebankgrupo1.databinding.ActivitySecurityBinding;

public class Security extends AppCompatActivity {

    private ActivitySecurityBinding binding;
    private Switch btnBiometria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });

        btnBiometria = findViewById(R.id.switch_biometria);

        btnBiometria.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                
            }
        });

    }

    public boolean habilitarBiometria() {
        boolean habilit = btnBiometria.isChecked();
        return habilit;
    }

    /*public boolean desabilitarBiometria() {
        boolean habilit = btnBiometria.;
        return habilit;
    }*/

    /*public boolean isaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(boolean aSwitch) {
        this.aSwitch = aSwitch;
    }

    public boolean habilitarBiometria() {
        binding.switch1.setOnClickListener(v -> {
            if (!isaSwitch()) {
                setaSwitch(true);
            }else {
                setaSwitch(false);
            }
        });
        return habilitarBiometria();
    }*/
}