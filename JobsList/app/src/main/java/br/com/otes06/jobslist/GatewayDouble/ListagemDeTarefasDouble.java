package br.com.otes06.jobslist.GatewayDouble;

import java.util.LinkedList;
import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.ListagemDeTarefasInterface;
import br.com.otes06.jobslist.Structs.TarefaStruct;

public class ListagemDeTarefasDouble implements ListagemDeTarefasInterface {

    @Override
    public List<TarefaStruct> buscarTodasAsTarefas() {
        List<TarefaStruct> list = new LinkedList<TarefaStruct>();

        for(int i = 0; i < 20; i++){
            TarefaStruct tarefa = new TarefaStruct();
            tarefa.setTitulo("Título" + i);
            list.add(tarefa);
        }

        return list;
    }

    @Override
    public TarefaStruct buscarPoId(int id) {
        return null;
    }
}
