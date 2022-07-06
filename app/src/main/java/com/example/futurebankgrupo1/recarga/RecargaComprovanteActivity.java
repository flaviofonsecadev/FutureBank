package com.example.futurebankgrupo1.recarga;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecargaComprovanteActivity extends AppCompatActivity {

    private ActivityRecargaComprovanteBinding binding;
    private MyViewModel viewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private int codComprovante = 1;

    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    Date data = new Date();
    String dataFormatada = formataData.format(data);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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


        //Salvar contador para o código do comprovante
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("chaveCodComprovante", String.valueOf(codComprovante));


//        //puxa dados tela p/ comprovante
        binding.tvGetTelRecebedor.getText().toString();
        binding.tvGetOperRecebedora.getText().toString();
        binding.tvGetValorRecarga.getText().toString();
        binding.tvGetConta1.getText().toString(); //tipo de pagamento
        binding.tvGetPagador.getText().toString();

        binding.tvGetDataHora.setText("Data " + dataFormatada);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null) {
                    String nome = userProfile.getNome();

                    binding.tvGetPagador.setText(nome);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecargaComprovanteActivity.this, "Ocorreu algum erro com o nome do pagador!", Toast.LENGTH_SHORT).show();
            }
        });

        //----------------------------------------------------- PrintScreen ----------------------------------------------------------------------------------

        /*verifyStoragePermission(this);

        binding.btnGerarPrint.setOnClickListener(v -> {
            binding.btnGerarPdf.setVisibility(View.GONE);
            binding.btnGerarPrint.setVisibility(View.GONE);
            binding.icClear.setVisibility(View.GONE);

            takeScreenShot(getWindow().getDecorView().getRootView(), "FutureBANK_recarga_");

            //Abrir imagem na galeria
            //startActivity(new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));

            new Handler().postDelayed(() -> {
                binding.btnGerarPdf.setVisibility(View.VISIBLE);
                binding.btnGerarPrint.setVisibility(View.VISIBLE);
                binding.icClear.setVisibility(View.VISIBLE);
            }, 1000);

        });*/

        //----------------------------------------------------- PrintScreen ----------------------------------------------------------------------------------


        //----------------------------------------------------- PDF----------------------------------------------------------------------------------

        binding.btnGerarPdf.setOnClickListener(v -> {
            gerarPDF();
            codComprovante++;
        });


    }//Fim onCreate



    private void gerarPDF() {

        SimpleDateFormat formatDate = new SimpleDateFormat("dd_MM_yyyy");
        Date data = new Date();
        String dateFormat = formatDate.format(data);


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

        //Topo
        canvas.drawText(binding.tvFuture.getText().toString(),20, 50, corDoTexto);


        //Lado esquerdo
        canvas.drawText(binding.tvRecargaRealizada.getText().toString(), 20, 80, corDoTexto);
        canvas.drawText(binding.tvGetDataHora.getText().toString(), 20, 96, corCinzaTexto);

        //separador
        canvas.drawText("______________________________________________________________",20, 134, corCinzaTexto);

        canvas.drawText(binding.tvTelRecebedor.getText().toString(), 20, 166, corCinzaTexto);
        canvas.drawText(binding.tvOperRecebora.getText().toString(), 20, 182, corCinzaTexto);
        canvas.drawText(binding.tvValorRecarga.getText().toString(), 20, 198, corCinzaTexto);
        canvas.drawText(binding.tvTipoPagamento.getText().toString(), 20, 214, corCinzaTexto);
        canvas.drawText(binding.tvPagador.getText().toString(), 20, 278, corCinzaTexto);
        canvas.drawText(binding.tvInstituicao.getText().toString(), 20, 294, corCinzaTexto);
        canvas.drawText(binding.tvAgencia.getText().toString(), 20, 310, corCinzaTexto);
        canvas.drawText(binding.tvContaCorrente.getText().toString(), 20, 326, corCinzaTexto);

        //separador
        canvas.drawText("______________________________________________________________",20, 242, corCinzaTexto);

        //Lado Direito
        canvas.drawText(binding.tvGetTelRecebedor.getText().toString(), 160, 166, corDoTexto);
        canvas.drawText(binding.tvGetOperRecebedora.getText().toString(), 160, 182, corDoTexto);
        canvas.drawText(binding.tvGetValorRecarga.getText().toString(), 160, 198, corDoTexto);
        canvas.drawText(binding.tvGetConta1.getText().toString(), 160, 214, corDoTexto);
        canvas.drawText(binding.tvGetPagador.getText().toString(), 160, 278, corDoTexto);
        canvas.drawText(binding.tvGetInstituicao.getText().toString(), 160, 294, corDoTexto);
        canvas.drawText(binding.tvGetAgencia.getText().toString(), 160, 310, corDoTexto);
        canvas.drawText(binding.tvGetConta.getText().toString(), 160, 326, corDoTexto);



        documentoPDF.finishPage(novaPagina);

        String targetPdf = "/storage/emulated/0/Download/FutureBANK_recarga_" + dateFormat + "_" + codComprovante + ".pdf";
        File filePath = new File(targetPdf);

        try {
            documentoPDF.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF salvo na pasta de download do seu dispositivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao gerar PDF" + e, Toast.LENGTH_LONG).show();
        }
    }

    //----------------------------------------------------- PDF ----------------------------------------------------------------------------------

    //----------------------------------------------------- PrintScreen ----------------------------------------------------------------------------------

    /*protected static File takeScreenShot(View view, String fileName) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd_MM_yyyy");
        Date data = new Date();
        String dateFormat = formatDate.format(data);

        try{

            String dirPath = Environment.getExternalStorageDirectory().toString() + "/comprovanteRecarga";
            File fileDir = new File(dirPath);
            if(!fileDir.exists()) {
                boolean mkdir = fileDir.mkdir();
            }

            String path = dirPath + "/" + fileName + dateFormat + ".jpeg";
            view .setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            File imageFile = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return imageFile;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity) {

        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }*/


    //----------------------------------------------------- PrintScreen ----------------------------------------------------------------------------------


}