package com.gustavo.mobdoc_v0;

/**
 * Created by Gustavo on 27/09/2017.
 */

public class DadosUpload {
    public String titulo;
    public String descricao;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public DadosUpload(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public DadosUpload(){

    }
}
