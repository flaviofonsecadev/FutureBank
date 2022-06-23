package com.example.futurebankgrupo1.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.futurebankgrupo1.R;

import java.util.List;

public class AdapterChavePix extends RecyclerView.Adapter<AdapterChavePix.MyViewHolder> {

    private List<RecyclerChavePix> listaChavePix;

    public AdapterChavePix(List<RecyclerChavePix> lista) {
        this.listaChavePix = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_chave_pix, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        RecyclerChavePix recyclerChavePix = listaChavePix.get(position);

        holder.tipoChave.setText(recyclerChavePix.getTipoChave());
        holder.chavePix.setText(recyclerChavePix.getChavePix());
    }

    @Override
    public int getItemCount() {
        return listaChavePix.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tipoChave;
        TextView chavePix;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tipoChave = itemView.findViewById(R.id.tv_tipo_de_chave_pix);
            chavePix = itemView.findViewById(R.id.tv_chave_pix);
        }
    }
}
