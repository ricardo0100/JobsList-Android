package br.com.otes06.jobslist.GatewayRealm;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.IGruposGateway;
import br.com.otes06.jobslist.GatewayRealm.Realms.GrupoRealm;
import br.com.otes06.jobslist.Structs.GrupoStruct;
import io.realm.Realm;


public class GruposGatewayRealm implements IGruposGateway {

    private Context context;

    public GruposGatewayRealm(Context context) {
        this.context = context;
    }

    @Override
    public List<GrupoStruct> buscarTodosOsGrupos() {
        Realm realm = Realm.getInstance(context);

        List<GrupoRealm> registros = realm.where(GrupoRealm.class).findAll();
        List<GrupoStruct> grupos = new ArrayList<GrupoStruct>();

        GrupoStruct grupo;
        for (GrupoRealm registro : registros) {
            grupo = new GrupoStruct();
            grupo.setId(registro.getId());
            grupo.setNome(registro.getNome());
            grupo.setUsuarioId(registro.getUsuarioId());
            grupo.setCreated(registro.getCreated());
            grupo.setModified(registro.getModified());
            grupos.add(grupo);
        }

        realm.close();

        return grupos;
    }

    @Override
    public GrupoStruct buscarPoId(int id) {
        Realm realm = Realm.getInstance(context);

        GrupoRealm registro = realm.where(GrupoRealm.class).equalTo("id", id).findFirst();
        GrupoStruct grupo = new GrupoStruct();

        grupo.setId(registro.getId());
        grupo.setNome(registro.getNome());
        grupo.setUsuarioId(registro.getUsuarioId());
        grupo.setCreated(registro.getCreated());
        grupo.setModified(registro.getModified());

        return grupo;
    }

    @Override
    public int salvar(GrupoStruct grupo) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        if (grupo.getId() == 0) {
            int size = ((int) realm.where(GrupoRealm.class).count());
            grupo.setId(size + 10000);
        }

        GrupoRealm registro = new GrupoRealm();
        registro.setId(grupo.getId());
        registro.setNome(grupo.getNome());
        registro.setUsuarioId(grupo.getUsuarioId());
        registro.setCreated(grupo.getCreated());
        registro.setModified(grupo.getModified());

        realm.copyToRealmOrUpdate(registro);
        realm.commitTransaction();
        realm.close();

        return grupo.getId();
    }
}
