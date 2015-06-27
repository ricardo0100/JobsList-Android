package br.com.otes06.jobslist;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.otes06.jobslist.Adapters.ListaDeGruposAdapter;
import br.com.otes06.jobslist.GatewayDouble.GruposGatewayDouble;
import br.com.otes06.jobslist.GatewayInterface.IGruposGateway;
import br.com.otes06.jobslist.GatewayRealm.GruposGatewayRealm;
import br.com.otes06.jobslist.Structs.GrupoStruct;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListaDeGruposFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ListaDeGruposFragment newInstance() {

        ListaDeGruposFragment fragment = new ListaDeGruposFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public ListaDeGruposFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_lista_de_grupos, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.listViewGrupos);
        IGruposGateway gateway = new GruposGatewayRealm(listView.getContext());
        List<GrupoStruct> grupos = gateway.buscarTodosOsGrupos();

        ListaDeGruposAdapter adapter = new ListaDeGruposAdapter(listView.getContext(), R.layout.rowgrupo, grupos);
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