package br.com.otes06.jobslist.GatewayRealm.Realms;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class TarefaRealm extends RealmObject{

    @PrimaryKey
    private int id;
    private String titulo;
    private String descricao;
    private boolean concluida;
    private int grupoId;
    private int usuarioId;
    private Date vencimento;
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

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
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

    public void setAlteradoLocal(boolean alteradoLocal) {
        this.alteradoLocal = alteradoLocal;
    }
}
