package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityComprovanteFaturaBinding;
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
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ComprovanteFatura extends AppCompatActivity {

    private ActivityComprovanteFaturaBinding binding;
    private MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Date data = new Date();
    String dataFormatada = formataData.format(data);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComprovanteFaturaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

//        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
//
//        binding.tvGetValorPago.setText(String.valueOf(viewModel.exibirValorFatura()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

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
                Toast.makeText(ComprovanteFatura.this, "Ocorreu algum erro com o nome do pagador!", Toast.LENGTH_SHORT).show();
            }
        });

        /*int day;
        int month;
        int year;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        day = preferences.getInt("chaveDay",0);
        month = preferences.getInt("chaveMonth",0);
        year= preferences.getInt("chaveYear", 0);
        binding.tvGetDataHora.setText(day + "/" +month+"/"+year);*/


        Intent intentReceberDados = getIntent();
        Bundle receberDados = intentReceberDados.getExtras();

        if (receberDados != null) {
            Float valorFatura = receberDados.getFloat("valorFatura");
            binding.tvGetValorPago.setText(dinheiroBR.format(valorFatura));
        }

        //Data e hora do pagamento
        binding.tvGetDataHora.setText("Data " + dataFormatada);

        //Gerar ID transação
        gerarIdTransacao();


        //----------------------------------------------------- PDF----------------------------------------------------------------------------------

        binding.btnGerarPdf.setOnClickListener(v -> {
            gerarPDF();
        });


    }//Fim onCreate

    private void gerarPDF() {

        String getIdNome = binding.tvGetId.getText().toString();

        PdfDocument documentoPDF = new PdfDocument();

        PdfDocument.PageInfo detalhesDaPagina = new PdfDocument.PageInfo.Builder(350, 600, 1).create();

        PdfDocument.Page novaPagina = documentoPDF.startPage(detalhesDaPagina);

        Canvas canvas = novaPagina.getCanvas();

        Paint corDoTexto = new Paint();
        corDoTexto.setColor(Color.BLACK);
        Paint corCinzaTexto = new Paint();
        corCinzaTexto.setColor(Color.GRAY);
        Paint corRosaTexto = new Paint();
        corRosaTexto.setColor(Color.MAGENTA);

        //Top
        canvas.drawText(binding.tvFuture.getText().toString(),20, 50, corDoTexto);


        //Lado esquerdo
        canvas.drawText(binding.tvPagamentoRealizado.getText().toString(), 20, 80, corDoTexto);
        canvas.drawText(binding.tvGetDataHora.getText().toString(), 20, 96, corCinzaTexto);

        //separador
        canvas.drawText("______________________________________________________________",20, 134, corCinzaTexto);

        canvas.drawText(binding.tvInstRecebedora.getText().toString(), 20, 166, corCinzaTexto);
        canvas.drawText(binding.tvCnpj.getText().toString(), 20, 182, corCinzaTexto);
        canvas.drawText(binding.tvValorPago.getText().toString(), 20, 198, corCinzaTexto);
        canvas.drawText(binding.tvTipoPagamento.getText().toString(), 20, 214, corCinzaTexto);

        canvas.drawText(binding.tvPagador.getText().toString(), 20, 278, corCinzaTexto);
        canvas.drawText(binding.tvInstituicao.getText().toString(), 20, 294, corCinzaTexto);
        canvas.drawText(binding.tvAgencia.getText().toString(), 20, 310, corCinzaTexto);
        canvas.drawText(binding.tvContaCorrente.getText().toString(), 20, 326, corCinzaTexto);

        //separador
        canvas.drawText("______________________________________________________________",20, 242, corCinzaTexto);

        //Lado Direito
        canvas.drawText(binding.tvGetInstRecebedora.getText().toString(), 160, 166, corDoTexto);
        canvas.drawText(binding.tvGetCnpj.getText().toString(), 160, 182, corDoTexto);
        canvas.drawText(binding.tvGetValorPago.getText().toString(), 160, 198, corDoTexto);
        canvas.drawText(binding.tvGetConta1.getText().toString(), 160, 214, corDoTexto);
        canvas.drawText(binding.tvGetPagador.getText().toString(), 160, 278, corDoTexto);
        canvas.drawText(binding.tvGetInstituicao.getText().toString(), 160, 294, corDoTexto);
        canvas.drawText(binding.tvGetAgencia.getText().toString(), 160, 310, corDoTexto);
        canvas.drawText(binding.tvGetConta.getText().toString(), 160, 326, corDoTexto);

        //Bottom
        canvas.drawText(binding.tvIdTransacao.getText().toString(), 20, 550, corCinzaTexto);
        canvas.drawText(binding.tvGetId.getText().toString(), 160, 550, corDoTexto);




        documentoPDF.finishPage(novaPagina);

        String targetPdf = "/storage/emulated/0/Download/recarga_" + getIdNome +  ".pdf";
        File filePath = new File(targetPdf);

        try {
            documentoPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF salvo na pasta de download do seu dispositivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao gerar PDF" + e, Toast.LENGTH_LONG).show();
        }
    }

    public String gerarIdTransacao() {
        Random rand = new Random();
        String letrasID = "";
        String numerosID = "";

        SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy-hhmmss");
        Date data = new Date();
        String dateFormat = formatDate.format(data);

        for (int i = 0; i < 3; i++) {
            char randomizedCharacter = (char) (rand.nextInt(26) + 'A');
            letrasID += randomizedCharacter;
        }

        for (int i = 0; i < 3; i++) {
            int value = rand.nextInt(9);
            numerosID += value;
        }
        binding.tvGetId.setText(letrasID + "-" + numerosID + "-" + dateFormat);
        return letrasID + "_" + numerosID;
    }
}