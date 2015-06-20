package br.com.otes06.jobslist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefas;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class ListaDeTarefas extends ActionBarActivity {

    ListView listView = null;

    ListaDeTarefasAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tarefas);

        this.listView = (ListView) findViewById(R.id.listView);

        IListagemDeTarefas gateway = new ListagemDeTarefasGatewayDouble();

        List<TarefaStruct> tarefas = gateway.buscarTodasAsTarefas();

        this.adapter = new ListaDeTarefasAdapter(this, R.layout.rowtarefa, tarefas);

        this.listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_de_tarefas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
