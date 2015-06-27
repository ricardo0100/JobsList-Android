package br.com.otes06.jobslist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import br.com.otes06.jobslist.GatewayDouble.ListagemDeGruposGatewayDouble;
import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.Structs.GrupoStruct;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class EdicaoTarefaActivity extends Activity {

    private int tarefaID;
    private TarefaStruct tarefa = null;
    private IListagemDeTarefasGateway tarefaGateway;

    private EditText tituloEditText;
    private EditText vencimentoEditText;
    private Spinner grupoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_tarefa);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        configurarStruct();

        this.tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        this.vencimentoEditText = (EditText) findViewById(R.id.vencimentoEditText);
        this.grupoSpinner = (Spinner) findViewById(R.id.spinnerGrupo);

        atualizarDados();
    }

    private void atualizarDados() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);

        String titulo = this.tarefa.getTitulo();
        String vencimento = simpleDateFormat.format(this.tarefa.getVencimento());


        List<GrupoStruct> list = new LinkedList<GrupoStruct>();
        list.add(GrupoStruct.SemGrupo());

        list.addAll(new ListagemDeGruposGatewayDouble().buscarTodosOsGrupos());

        ArrayAdapter<GrupoStruct> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.grupoSpinner.setAdapter(dataAdapter);

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
            case R.id.action_salvarTarefa:
                salvarTarefa();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarTarefa() {
        if(validarDados()){
            Toast.makeText(this,"Salvar Tarefa Não Implementado", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarDados() {
        if(this.tituloEditText.getText().toString().equals("")){
            this.tituloEditText.setError("Digite um título para a tarefa");
            return false;
        }
        return true;
    }

}
