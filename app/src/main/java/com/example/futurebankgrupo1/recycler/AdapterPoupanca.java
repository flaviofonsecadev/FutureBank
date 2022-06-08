package com.example.futurebankgrupo1.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futurebankgrupo1.R;

import java.util.List;

public class AdapterPoupanca extends RecyclerView.Adapter<AdapterPoupanca.MyViewHolder> {

    private List<RecyclerPoupanca> listaRecyclerPoupancas;

    public AdapterPoupanca(List<RecyclerPoupanca> lista) {
        this.listaRecyclerPoupancas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_poupanca, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RecyclerPoupanca recyclerPoupanca = listaRecyclerPoupancas.get(position);

        holder.guardado.setText(recyclerPoupanca.getValorGuardado());
        holder.data.setText(recyclerPoupanca.getData());
        holder.valor.setText(recyclerPoupanca.getValor());
        holder.imagem.setImageResource(recyclerPoupanca.getImagem());

    }

    @Override
    public int getItemCount() {
        return listaRecyclerPoupancas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView guardado;
        TextView valor;
        TextView data;
        ImageView imagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            guardado = itemView.findViewById(R.id.tv_guardado_resgatado);
            valor = itemView.findViewById(R.id.tv_valor);
            data = itemView.findViewById(R.id.tv_data);
            imagem = itemView.findViewById(R.id.iv_imagem);
        }
    }
}
