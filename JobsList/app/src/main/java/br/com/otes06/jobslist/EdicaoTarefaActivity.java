package br.com.otes06.jobslist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class EdicaoTarefaActivity extends Activity {

    private int tarefaID;
    private TarefaStruct tarefa = null;
    private IListagemDeTarefasGateway tarefaGateway;

    private EditText tituloEditText;
    private EditText vencimentoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_tarefa);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        configurarStruct();

        this.tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        this.vencimentoEditText = (EditText) findViewById(R.id.vencimentoEditText);
        atualizarDados();

    }

    private void atualizarDados() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);

        String titulo = this.tarefa.getTitulo();
        String vencimento = simpleDateFormat.format(this.tarefa.getVencimento());


        this.tituloEditText.setText(titulo);
        this.vencimentoEditText.setText(vencimento);

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
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
