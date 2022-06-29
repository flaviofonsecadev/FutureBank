package com.example.futurebankgrupo1.recarga;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityRecargaComprovanteBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecargaComprovanteActivity extends AppCompatActivity {

    //PDF
    //Button btnGerarPDF;
   // TextView txtTitulo;



    private ActivityRecargaComprovanteBinding binding;
    private MyViewModel viewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    //pdf
    private TextView myEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //PDF
       // iniciarAplicativo();


        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        String telefone;
        String operadora;
        String valorRecarga;
        String tipoPagamento;

        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);

        telefone = preferences.getString("chaveTelefone", "");
        binding.tvGetTelRecebedor.setText(telefone);

        operadora = preferences.getString("chaveOperadora", "");
        binding.tvGetOperRecebedora.setText(operadora);

        valorRecarga = preferences.getString("chaveValorRecarga", "");
        binding.tvGetValorRecarga.setText("R$" + valorRecarga);

        tipoPagamento = preferences.getString("chaveTipoPagamento", "");
        binding.tvGetConta1.setText(tipoPagamento);


//        //puxa dados tela p/ comprovante
//        binding.tvGetTelRecebedor.getText().toString();
//        binding.tvGetOperRecebedora.getText().toString();
//        binding.tvGetValorRecarga.getText().toString();
//        binding.tvGetConta1.getText().toString(); //tipo de pagamento
//        binding.tvGetPagador.getText().toString();


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();

                    binding.tvGetPagador.setText(nome);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecargaComprovanteActivity.this, "Ocorreu algum erro com o nome do pagador!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    //----------------------------------------------------- PDF----------------------------------------------------------------------------------
   // private void iniciarAplicativo() {
   // btnGerarPDF = findViewById(R.id.btn_gerar_pdf);
   // txtTitulo = findViewById(R.id.tv_recarga_realizada);
    }



    //public void gerarPDF(View view){
      //PdfDocument documentoPDF = new PdfDocument();

     // PdfDocument.PageInfo detalhesDaPagina = new PdfDocument.PageInfo.Builder(500,600,1).create();

      //PdfDocument.Page novaPagina = novaPagina = documentoPDF.startPage(detalhesDaPagina);

        //Canvas canvas = novaPagina.getCanvas();

       // Paint corDoTexto = new Paint();
       // corDoTexto.setColor(Color.BLACK);
       // canvas.drawText(txtTitulo.getText().toString(), 105, 100, corDoTexto);
       // corDoTexto.setColor(Color.BLACK);
       // documentoPDF.finishPage(novaPagina);

       // String targetPdf = "/sdcard/pdf/pdfModeloNovo.pdf";
        //File filePath = new File(targetPdf);

        //try{
         //   documentoPDF.writeTo(new FileOutputStream(filePath));
         //   Toast.makeText(this, "PDF gerado com sucesso. . .", Toast.LENGTH_LONG).show();
       // } catch (IOException e) {
         //   e.printStackTrace();
          //  Toast.makeText(this, "falha ao gerar o PDF: " + e.toString(), Toast.LENGTH_LONG).show();
        //}
   // }
    //----------------------------------------------------- PDF----------------------------------------------------------------------------------
}