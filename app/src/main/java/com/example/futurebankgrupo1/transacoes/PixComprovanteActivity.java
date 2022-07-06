package com.example.futurebankgrupo1.transacoes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityPixComprovanteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PixComprovanteActivity extends AppCompatActivity {
    private ActivityPixComprovanteBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPixComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnComprovantePix.setOnClickListener(v -> {

            gerarPDF();

            //Abrir imagem na galeria
            //startActivity(new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        });

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);
                if (userProfile != null){
                    String nome = userProfile.getNome();
                    String cpf = userProfile.getCpf();
                    binding.tvPagador.setText(nome);
                    binding.tvNumCpfPagador.setText(cpf);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PixComprovanteActivity.this, "Error Firebasse", Toast.LENGTH_SHORT).show();
            }
        });

        String valorPix;
        String nomeRecebedor;
        String mensagemPix;
        String date;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        nomeRecebedor = preferences.getString("chaveNomeRecebedor", "");
        mensagemPix = preferences.getString("chaveMensagemPix", "");
        date = preferences.getString("chaveDate", "");
        binding.tvGetValorTransferido.setText("R$" + valorPix);
        binding.tvRecebedor.setText(nomeRecebedor);
        binding.tvTipoPgto.setText(mensagemPix);
        binding.tvDataHora.setText(date);
    }

    private void gerarPDF() {
        //Cria um documento para gerar o PDF
        PdfDocument documentPDF = new PdfDocument();

        //Especifica detahes da página
        PdfDocument.PageInfo detalhesDaPagina = new PdfDocument.PageInfo.Builder(500, 900, 1).create();

        //Criando a página
        PdfDocument.Page novaPagina = documentPDF.startPage(detalhesDaPagina);

        Canvas canvas = novaPagina.getCanvas();

        Paint corTexto = new Paint();
        corTexto.setColor(Color.BLACK);

        canvas.drawText(binding.tvTransferenciaPix.getText().toString(), 105, 100, corTexto);
        corTexto.setColor(Color.BLACK);

        documentPDF.finishPage(novaPagina);

        //Criar o caminho onde será salvo
        String targetPdf = "/storage/emulated/0/Download/pdfModeloNovo.pdf";
        File filePath = new File(targetPdf);

        try {
            documentPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF gerado com sucesso. . .", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao gerar PDF" + e, Toast.LENGTH_LONG).show();
        }
    }
}



















