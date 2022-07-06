package com.example.futurebankgrupo1.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futurebankgrupo1.R;

import java.util.List;

public class AdapterCorrente extends RecyclerView.Adapter<AdapterCorrente.MyViewHolder> {

    //adicionei o context
    private Context mContext;
    private List<RecyclerCorrente> listaCorrente;

    public AdapterCorrente(Context mContext, List<RecyclerCorrente> lista) {
        this.mContext = mContext;
        this.listaCorrente = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(mContext).inflate(R.layout.adapter_lista_corrente, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemLista);
        return myViewHolder;
        //return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final RecyclerCorrente recyclerCorrente = listaCorrente.get(position);


        holder.creditoDebito.setText(recyclerCorrente.getTransacaoCreditoDebito());
        holder.valor.setText(recyclerCorrente.getValor());
        holder.data.setText(recyclerCorrente.getData());
        //holder.imagem.setImageResource(recyclerCorrente.getImagem());

        //adicionei o switch para setar a imagem  e comentei o holder da imagem acima
        switch (recyclerCorrente.getTransacaoCreditoDebito()){
            case "Transferência recebida":
            case "Resgate da poupança":
                holder.imagem.setImageResource(R.drawable.ic_money_verde);
                break;
            case "Transferência enviada":
            default:
                holder.imagem.setImageResource(R.drawable.ic__money_vermelho);
        }

    }

    @Override
    public int getItemCount() {
        return listaCorrente.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView creditoDebito;
        TextView valor;
        TextView data;
        ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            creditoDebito = itemView.findViewById(R.id.tv_credito_debito);
            valor = itemView.findViewById(R.id.tv_valor);
            data = itemView.findViewById(R.id.tv_data);
            imagem = itemView.findViewById(R.id.iv_imagem);
        }
    }
}
