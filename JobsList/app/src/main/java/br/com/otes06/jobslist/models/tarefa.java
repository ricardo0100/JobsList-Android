package br.com.otes06.jobslist.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Tarefa extends RealmObject{

    @PrimaryKey
    private int id;

    private String titulo;
    private String descricao;
    private Date vencimento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

}
