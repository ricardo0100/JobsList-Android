package br.com.otes06.jobslist.GatewayInterfaces;

import java.util.List;

import br.com.otes06.jobslist.Structs.TarefaStruct;

public interface IListagemDeTarefas {

    List<TarefaStruct> buscarTodasAsTarefas();

    TarefaStruct buscarPoId(int id);

}
