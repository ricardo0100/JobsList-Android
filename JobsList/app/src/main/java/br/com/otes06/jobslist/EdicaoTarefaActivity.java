package br.com.otes06.jobslist;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import br.com.otes06.jobslist.GatewayDouble.GruposGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.ITarefasGateway;
import br.com.otes06.jobslist.GatewayRealm.TarefasGatewayRealm;
import br.com.otes06.jobslist.Structs.GrupoStruct;
import br.com.otes06.jobslist.Structs.TarefaStruct;


public class EdicaoTarefaActivity extends Activity {

    private int tarefaID;
    private TarefaStruct tarefa = null;
    private ITarefasGateway tarefaGateway;

    private EditText tituloEditText;
    private TextView vencimentoTextView;
    private Button botaoDefinirData;
    private Button botaoDefinirHora;
    private Spinner grupoSpinner;
    private Switch switchConcluido;
    private EditText editTextDescricao;

    private Calendar vencimento;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicao_tarefa);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        this.vencimentoTextView = (TextView) findViewById(R.id.vencimentoTextView);
        this.botaoDefinirData = (Button) findViewById(R.id.buttonDefinirData);
        this.botaoDefinirHora = (Button) findViewById(R.id.buttonDefinirHora);
        this.grupoSpinner = (Spinner) findViewById(R.id.spinnerGrupo);
        this.switchConcluido = (Switch) findViewById(R.id.switchConcluido);
        this.editTextDescricao = (EditText) findViewById(R.id.editTextDescricao);

        carregarSpinnerGrupos();

        carregarDados();

        configurarDatePickerVencimento();
        configurarTimePickerVencimento();

    }

    //DatePickerDialog
    private void configurarDatePickerVencimento() {
        this.botaoDefinirData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), dateDialogListener(), vencimento.get(Calendar.YEAR), vencimento.get(Calendar.MONTH), vencimento.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateDialogListener() {
        return new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                vencimento.set(Calendar.YEAR, year);
                vencimento.set(Calendar.MONTH, monthOfYear);
                vencimento.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                atualizarLabelVencimento(vencimento.getTime());
            }
        };
    }

    //TimePickerDialog
    private void configurarTimePickerVencimento() {
        this.botaoDefinirHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(v.getContext(), timeDialogListener(), vencimento.get(Calendar.HOUR_OF_DAY), vencimento.get(Calendar.MINUTE), true).show();
            }
        });
    }

    private TimePickerDialog.OnTimeSetListener timeDialogListener() {
        return new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                vencimento.set(Calendar.HOUR_OF_DAY, hourOfDay);
                vencimento.set(Calendar.MINUTE, minute);
                atualizarLabelVencimento(vencimento.getTime());
            }
        };
    }

    private void atualizarLabelVencimento(Date vencimento) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
        this.vencimentoTextView.setText(simpleDateFormat.format(vencimento));
    }

    private void carregarSpinnerGrupos() {
        List<GrupoStruct> list = new LinkedList<>();
        list.add(GrupoStruct.SemGrupo());

        list.addAll(new GruposGatewayDouble().buscarTodosOsGrupos());

        ArrayAdapter<GrupoStruct> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.grupoSpinner.setAdapter(dataAdapter);
    }


    private void carregarDados() {
        this.tarefaGateway = new TarefasGatewayRealm(this);
        Intent intent = getIntent();
        this.tarefaID = intent.getIntExtra("tarefaID", 0);

        if (this.isNovaTarefa()) {
            this.tarefa = new TarefaStruct();
        } else {
            this.tarefa = this.tarefaGateway.buscarPoId(tarefaID);
        }

        //Carrega controles
        this.tituloEditText.setText(this.tarefa.getTitulo());

        if (this.vencimento == null)
            this.vencimento = Calendar.getInstance();
        this.vencimento.setTime(this.tarefa.getVencimento());
        atualizarLabelVencimento(this.vencimento.getTime());

        this.position = 0;
        for (int i = 0; i < this.grupoSpinner.getCount(); i++) {
            if (((GrupoStruct) this.grupoSpinner.getItemAtPosition(i)).getId() == this.tarefa.getGrupoId()) {
                this.position = i;
                break;
            }
        }
        this.grupoSpinner.post(new Runnable() {
            @Override
            public void run() {
                grupoSpinner.setSelection(position);
            }
        });

        this.switchConcluido.setChecked(this.tarefa.getConcluida());

        this.editTextDescricao.setText(this.tarefa.getDescricao());
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
        if (validarDados()) {
            this.tarefa.setTitulo(this.tituloEditText.getText().toString());
            this.tarefa.setVencimento(this.vencimento.getTime());
            this.tarefa.setGrupo((GrupoStruct) this.grupoSpinner.getSelectedItem());
            this.tarefa.setConcluida(switchConcluido.isChecked());
            this.tarefa.setDescricao(this.editTextDescricao.getText().toString());

            this.tarefaGateway.salvar(this.tarefa);
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    private boolean validarDados() {
        if (this.tituloEditText.getText().toString().equals("")) {
            this.tituloEditText.setError("Digite um título para a tarefa");
            return false;
        }
        return true;
    }

}
