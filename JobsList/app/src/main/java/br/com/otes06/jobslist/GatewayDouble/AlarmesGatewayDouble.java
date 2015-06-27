package br.com.otes06.jobslist.GatewayDouble;

import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.IAlarmesGateway;
import br.com.otes06.jobslist.Structs.AlarmeStruct;


public class AlarmesGatewayDouble implements IAlarmesGateway {

    @Override
    public List<AlarmeStruct> buscarTodosOsAlarmes() {
        return null;
    }

    @Override
    public AlarmeStruct buscarPorId(int id) {
        return null;
    }

    @Override
    public int salvar(AlarmeStruct alarme) {
        return 0;
    }
}
