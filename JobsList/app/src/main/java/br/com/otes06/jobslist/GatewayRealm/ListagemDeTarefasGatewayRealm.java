package br.com.otes06.jobslist.GatewayRealm;

import java.util.List;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class ListagemDeTarefasGatewayRealm implements IListagemDeTarefasGateway {
    @Override
    public List<TarefaStruct> buscarTodasAsTarefas() {
        return null;
    }

    @Override
    public TarefaStruct buscarPoId(int id) {
        return null;
    }
}
