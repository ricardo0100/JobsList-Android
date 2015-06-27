package br.com.otes06.jobslist.GatewayRealm.Realms;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class AlarmeRealm extends RealmObject {

    @PrimaryKey
    private int id;
    private Date horario;
    private boolean ativo;
    private int tarefaId;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public int getTarefaId() {
        return tarefaId;
    }

    public void setTarefaId(int tarefaId) {
        this.tarefaId = tarefaId;
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
