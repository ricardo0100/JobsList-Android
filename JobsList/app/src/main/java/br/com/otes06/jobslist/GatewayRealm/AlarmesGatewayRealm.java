package br.com.otes06.jobslist.GatewayRealm;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.IAlarmesGateway;
import br.com.otes06.jobslist.GatewayRealm.Realms.AlarmeRealm;
import br.com.otes06.jobslist.Structs.AlarmeStruct;
import io.realm.Realm;


public class AlarmesGatewayRealm implements IAlarmesGateway {

    private Context context;

    public AlarmesGatewayRealm(Context context) {
        this.context = context;
    }

    @Override
    public List<AlarmeStruct> buscarTodosOsAlarmes() {
        Realm realm = Realm.getInstance(context);

        List<AlarmeRealm> registros = realm.where(AlarmeRealm.class).findAll();
        List<AlarmeStruct> alarmes = new ArrayList<AlarmeStruct>();

        AlarmeStruct alarme;
        for (AlarmeRealm registro : registros) {
            alarme = new AlarmeStruct();
            alarme.setId(registro.getId());
            alarme.setHorario(registro.getHorario());
            alarme.setAtivo(registro.isAtivo());
            alarme.setTarefaId(registro.getTarefaId());
            alarme.setUsuarioId(registro.getUsuarioId());
            alarme.setCreated(registro.getCreated());
            alarme.setModified(registro.getModified());
            alarmes.add(alarme);
        }

        realm.close();
        return alarmes;
    }

    @Override
    public AlarmeStruct buscarPorId(int id) {
        Realm realm = Realm.getInstance(context);

        AlarmeRealm registro = realm.where(AlarmeRealm.class).equalTo("id", id).findFirst();
        AlarmeStruct alarme = new AlarmeStruct();

        alarme.setId(registro.getId());
        alarme.setHorario(registro.getHorario());
        alarme.setAtivo(registro.isAtivo());
        alarme.setTarefaId(registro.getTarefaId());
        alarme.setUsuarioId(registro.getUsuarioId());
        alarme.setCreated(registro.getCreated());
        alarme.setModified(registro.getModified());

        realm.close();
        return alarme;
    }

    @Override
    public int salvar(AlarmeStruct alarme) {
        Realm realm = Realm.getInstance(context);
        realm.beginTransaction();

        if (alarme.getId() == 0) {
            int size = ((int) realm.where(AlarmeRealm.class).count());
            alarme.setId(size + 10000);
        }

        AlarmeRealm registro = new AlarmeRealm();
        registro.setId(alarme.getId());
        registro.setHorario(alarme.getHorario());
        registro.setAtivo(alarme.isAtivo());
        registro.setTarefaId(alarme.getTarefaId());
        registro.setUsuarioId(alarme.getUsuarioId());
        registro.setCreated(alarme.getCreated());
        registro.setModified(alarme.getModified());

        realm.copyToRealmOrUpdate(registro);
        realm.commitTransaction();
        realm.close();

        return alarme.getId();
    }
}
