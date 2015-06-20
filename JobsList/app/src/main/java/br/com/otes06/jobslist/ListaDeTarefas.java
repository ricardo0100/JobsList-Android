package br.com.otes06.jobslist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class ListaDeTarefas extends Activity {

    ListView listView = null;

    ListaDeTarefasAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tarefas);

        this.listView = (ListView) findViewById(R.id.listView);

        final IListagemDeTarefasGateway gateway = new ListagemDeTarefasGatewayDouble();

        List<TarefaStruct> tarefas = gateway.buscarTodasAsTarefas();

        this.adapter = new ListaDeTarefasAdapter(this, R.layout.rowtarefa, tarefas);

        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                TarefaStruct tarefa = (TarefaStruct) listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), tarefa.getTitulo(), Toast.LENGTH_SHORT).show();

            }
        });

        getActionBar().setTitle("Hey");

    }
}
