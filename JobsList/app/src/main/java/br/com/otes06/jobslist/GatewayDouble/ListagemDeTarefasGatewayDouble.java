package br.com.otes06.jobslist.GatewayDouble;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.TarefaStruct;

public class ListagemDeTarefasGatewayDouble implements IListagemDeTarefasGateway {

    @Override
    public List<TarefaStruct> buscarTodasAsTarefas() {
        List<TarefaStruct> list = new LinkedList<TarefaStruct>();

        for(int i = 1; i <= 20; i++){
            TarefaStruct tarefa = new TarefaStruct();
            tarefa.setId(i);
            tarefa.setTitulo("Tarefa " + i);
            tarefa.setVencimento(new Date());
            list.add(tarefa);
        }

        return list;
    }

    @Override
    public TarefaStruct buscarPoId(int id) {
        TarefaStruct tarefa = new TarefaStruct();
        tarefa.setId(id);
        tarefa.setTitulo("Tarefa ID:" + id);
        tarefa.setVencimento(new Date());

        return tarefa;
    }

}
