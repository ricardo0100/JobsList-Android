package br.com.otes06.jobslist.GatewayRealm.Realms;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class GrupoRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private String nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public boolean isAlteradoLocal() {
        return alteradoLocal;
    }

    public void setAlteradoLocal(boolean alteradoLocal) {
        this.alteradoLocal = alteradoLocal;
    }
}
