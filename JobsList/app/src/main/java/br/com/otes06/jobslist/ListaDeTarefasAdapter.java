package br.com.otes06.jobslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.otes06.jobslist.Structs.TarefaStruct;

public class ListaDeTarefasAdapter extends ArrayAdapter<TarefaStruct> {

    private List<TarefaStruct> tarefas = null;

    public ListaDeTarefasAdapter(Context context, int resource, List<TarefaStruct> tarefas) {
        super(context, resource, tarefas);

        this.tarefas = tarefas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.rowtarefa, null);
        }

        TarefaStruct tarefa = tarefas.get(position);

        TextView tituloView = (TextView) v.findViewById(R.id.tituloTarefa);
        tituloView.setText(tarefa.getTitulo());

        return v;
    }

}
