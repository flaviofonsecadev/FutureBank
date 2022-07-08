package com.example.futurebankgrupo1.transacoes;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityTelaPagarBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFatura;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class TelaPagar extends AppCompatActivity {

    ImageView tv_seta_qr;

    private ActivityTelaPagarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaPagarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //SCANNER
        tv_seta_qr = (ImageView) findViewById(R.id.tv_seta_qr);
        tv_seta_qr.setOnClickListener(view1 -> {
            scanCode();
        });



        //BINDING
        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagarComPix.class);
            startActivity(intent);
        });

        binding.ivArrowForward2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFatura.class);
            startActivity(intent);
        });

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.tvSetaQr.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CaptureQr.class);
            startActivity(intent);
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureQr.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
       if(result.getContents() !=null){
           AlertDialog.Builder builder = new AlertDialog.Builder(TelaPagar.this);
           builder.setTitle("Result");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i)
               {
                dialogInterface.dismiss();
               }
           }).show();
       }
    });
}
















































































































































































































































































