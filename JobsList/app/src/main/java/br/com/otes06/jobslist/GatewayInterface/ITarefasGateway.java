package br.com.otes06.jobslist.GatewayInterface;

import java.util.List;

import br.com.otes06.jobslist.Structs.TarefaStruct;

public interface ITarefasGateway {

    List<TarefaStruct> buscarTodasAsTarefas();

    TarefaStruct buscarPoId(int id);

    int salvar(TarefaStruct tarefa);

}
