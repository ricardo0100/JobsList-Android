package br.com.otes06.jobslist.Structs;

import java.util.Date;

public class TarefaStruct {

    private int id;
    private String titulo;
    private String descricao;
    private Date vencimento;
    private boolean concluida;
    private GrupoStruct grupo = null;
    private int grupoId;
    private int usuarioId;
    private Date created;
    private Date modified;
    private boolean alteradoLocal;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public boolean getAlteradoLocal() {
        return alteradoLocal;
    }

    public void setAlteradoLocal(Boolean alteradoLocal) {
        this.alteradoLocal = alteradoLocal;
    }

    public GrupoStruct getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoStruct grupo) {
        this.grupo = grupo;
        this.setGrupoId(grupo.getId());
    }
}
