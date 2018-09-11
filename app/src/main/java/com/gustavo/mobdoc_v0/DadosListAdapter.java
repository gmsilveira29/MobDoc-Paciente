package com.gustavo.mobdoc_v0;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.gustavo.mobdoc_v0.R.id.tvDescricaoDados;
import static com.gustavo.mobdoc_v0.R.id.tvTituloDados;

/**
 * Created by Gustavo on 27/09/2017.
 */

public class DadosListAdapter extends ArrayAdapter<DadosUpload> {
    private Activity context;
    private int resource;
    private List<DadosUpload> listDados;


    public DadosListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<DadosUpload> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listDados = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, null);
        TextView tvTitulo = (TextView) v.findViewById(tvTituloDados);
        TextView tvDescricao = (TextView) v.findViewById(tvDescricaoDados);

        tvTitulo.setText(listDados.get(position).getTitulo());
        tvDescricao.setText(listDados.get(position).getDescricao());

        return v;
    }
}
