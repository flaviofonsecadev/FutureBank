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

public class AdapterCompra extends RecyclerView.Adapter<AdapterCompra.MyViewHolder> {

    private List<Compra> listaCompras;

    public AdapterCompra(List<Compra> lista){
        this.listaCompras = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_compra, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Compra compra = listaCompras.get(position);
        holder.compra.setText(compra.getTituloFilme());
        holder.data.setText(compra.getGenero());
        holder.valor.setText(compra.getAno());
        holder.foto.setImageResource(compra.getFoto());

    }

    @Override
    public int getItemCount() {
        return listaCompras.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView compra;
        TextView valor;
        TextView data;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            compra = itemView.findViewById(R.id.tv_compra);
            valor = itemView.findViewById(R.id.tv_valor);
            data = itemView.findViewById(R.id.tv_data);
            foto = itemView.findViewById(R.id.iv_imagem);

        }
    }
}
