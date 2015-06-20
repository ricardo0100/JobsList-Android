package br.com.otes06.jobslist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class JobsList extends Activity {

    private Button buttonListaDeTarefas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_list);

        buttonListaDeTarefas = (Button) findViewById(R.id.buttonListaDeTarefas);
        buttonListaDeTarefas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListaDeTarefas.class);
                startActivity(intent);
            }
        });
    }

}
