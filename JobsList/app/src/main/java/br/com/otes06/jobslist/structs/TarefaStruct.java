package br.com.otes06.jobslist.Structs;

import java.util.Date;

public class TarefaStruct {

    private int id;

    private String titulo;
    private String descricao;
    private Date vencimento;
    private GrupoStruct grupo = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public GrupoStruct getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoStruct grupo) {
        this.grupo = grupo;
    }

}
