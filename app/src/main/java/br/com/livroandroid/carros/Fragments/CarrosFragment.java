package br.com.livroandroid.carros.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.adapter.CarroAdapter;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.domain.CarroService;

public class CarrosFragment extends BaseFragment {
    private String tipo;
    protected RecyclerView recyclerView;
    private List<Carro> carros;
    // Método para instanciar esse fragment pelo tipo.
    public static CarrosFragment newInstance(String tipo) {
        Bundle args = new Bundle();
        args.putString("tipo", tipo);
        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Lê o tipo dos argumentos.
            this.tipo = getArguments().getString("tipo");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        taskCarros();
    }
    private void taskCarros(){
        this.carros = CarroService.getCarros(getContext(),tipo);
        recyclerView.setAdapter(new CarroAdapter(getContext(), carros, onClickCarro()));
    }
    private CarroAdapter.CarroOnClickListener onClickCarro (){
        return new CarroAdapter.CarroOnClickListener(){
            @Override
            public void onClickCarro(View view, int idx){
                Carro c = carros.get(idx);
                Toast.makeText(getContext(), "Carro: " + c.nome, Toast.LENGTH_SHORT).show();
            }
        };
    }

}