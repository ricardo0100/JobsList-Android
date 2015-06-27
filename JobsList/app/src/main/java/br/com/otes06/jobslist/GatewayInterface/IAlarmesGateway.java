package br.com.otes06.jobslist.GatewayInterface;

import java.util.List;

import br.com.otes06.jobslist.Structs.AlarmeStruct;

public interface IAlarmesGateway {
    List<AlarmeStruct> buscarTodosOsAlarmes();
    AlarmeStruct buscarPorId(int id);
    int salvar(AlarmeStruct alarme);
}
