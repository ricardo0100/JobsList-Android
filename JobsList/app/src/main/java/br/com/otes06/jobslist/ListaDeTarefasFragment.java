package br.com.otes06.jobslist;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.otes06.jobslist.Adapters.ListaDeTarefasAdapter;
import br.com.otes06.jobslist.GatewayDouble.ListagemDeTarefasGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IListagemDeTarefasGateway;
import br.com.otes06.jobslist.GatewayRealm.TarefasGatewayRealm;
import br.com.otes06.jobslist.Structs.TarefaStruct;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListaDeTarefasFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ListaDeTarefasFragment newInstance() {

        ListaDeTarefasFragment fragment = new ListaDeTarefasFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public ListaDeTarefasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_lista_de_tarefas, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listViewTarefas);
        IListagemDeTarefasGateway gateway = new TarefasGatewayRealm(listView.getContext());
        List<TarefaStruct> tarefas = gateway.buscarTodasAsTarefas();

        ListaDeTarefasAdapter adapter = new ListaDeTarefasAdapter(listView.getContext(), R.layout.rowtarefa, tarefas);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}