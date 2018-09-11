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

import static com.gustavo.mobdoc_v0.R.id.tvDescProntuario;
import static com.gustavo.mobdoc_v0.R.id.tvTitProntuario;

/**
 * Created by Gustavo on 29/09/2017.
 */

public class ProntuarioListAdapter extends ArrayAdapter<ProntuarioUpload> {
    private Activity context;
    private int resource;
    private List<ProntuarioUpload> listProntuario;

    public ProntuarioListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<ProntuarioUpload> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listProntuario = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater2 = context.getLayoutInflater();

        View v = inflater2.inflate(resource, null);
        TextView tvName = (TextView) v.findViewById(tvTitProntuario);
        TextView tvDesc = (TextView) v.findViewById(tvDescProntuario);

        tvName.setText(listProntuario.get(position).getName());
        tvDesc.setText(listProntuario.get(position).getDes());

        return v;
    }
}

