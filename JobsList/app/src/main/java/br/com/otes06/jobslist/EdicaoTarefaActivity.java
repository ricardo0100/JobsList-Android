package br.com.otes06.jobslist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.otes06.jobslist.GatewayDouble.ListagemDeGruposGatewayDouble;
import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeGruposGateway;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class EdicaoTarefaActivity extends Activity {

    private int tarefaID;
    private TarefaStruct tarefa = null;
    private IListagemDeTarefasGateway tarefaGateway;

    private EditText tituloEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_tarefa);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        configurarStruct();
        configurarControles();

    }

    private void configurarControles() {
        this.tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        String titulo = this.tarefa.getTitulo();
        this.tituloEditText.setText(titulo);
    }

    private void configurarStruct() {
        this.tarefaGateway = new ListagemDeTarefasGatewayDouble(); //TODO

        Intent intent = getIntent();

        this.tarefaID = intent.getIntExtra("tarefaID", 0);

        if(this.isNovaTarefa()){
            this.tarefa = new TarefaStruct();
        } else {
            this.tarefa = this.tarefaGateway.buscarPoId(tarefaID);
        }
    }

    private boolean isNovaTarefa() {
        return this.tarefaID == 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edicao_tarefa, menu);
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

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("highlightID", this.tarefaID);
        return intent;
    }

}
