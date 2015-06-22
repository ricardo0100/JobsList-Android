package br.com.otes06.jobslist.GatewayDouble;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.otes06.jobslist.GatewayInterface.IListagemDeGruposGateway;
import br.com.otes06.jobslist.Structs.GrupoStruct;
import br.com.otes06.jobslist.Structs.TarefaStruct;

/**
 * Created by ricardo on 21/06/15.
 */
public class ListagemDeGruposGatewayDouble implements IListagemDeGruposGateway{
    @Override
    public List<GrupoStruct> buscarTodosOsGrupos() {
        List<GrupoStruct> list = new LinkedList<GrupoStruct>();

        for(int i = 1; i <= 20; i++){
            GrupoStruct grupo = new GrupoStruct();
            grupo.setNome("Grupo " + i);
            list.add(grupo);
        }

        return list;
    }

    @Override
    public GrupoStruct buscarPoId(int id) {
        GrupoStruct grupo = new GrupoStruct();
        grupo.setNome("Grupo ID: " + id);

        return grupo;
    }
}
