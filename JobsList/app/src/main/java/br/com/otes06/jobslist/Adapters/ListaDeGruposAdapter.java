package br.com.otes06.jobslist.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.com.otes06.jobslist.R;
import br.com.otes06.jobslist.Structs.GrupoStruct;

public class ListaDeGruposAdapter extends ArrayAdapter<GrupoStruct> {

    private List<GrupoStruct> grupos = null;

    public ListaDeGruposAdapter(Context context, int resource, List<GrupoStruct> grupos) {
        super(context, resource, grupos);

        this.grupos = grupos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.rowgrupo, null);
        }

        GrupoStruct tarefa = grupos.get(position);

        TextView tituloView = (TextView) v.findViewById(R.id.nomeGrupo);
        tituloView.setText(tarefa.getNome());

        return v;
    }

}
