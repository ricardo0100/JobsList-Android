package br.com.otes06.jobslist.GatewayInterface;

import java.util.List;

import br.com.otes06.jobslist.Structs.GrupoStruct;

/**
 * Created by ricardo on 21/06/15.
 */
public interface IGruposGateway {
    List<GrupoStruct> buscarTodosOsGrupos();

    GrupoStruct buscarPoId(int id);

    int salvar(GrupoStruct grupo);
}
