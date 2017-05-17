package br.com.livroandroid.carros.activity;

import android.os.Parcel;
import org.parceler.Parcels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.livroandroid.carros.Fragments.CarroFragment;
import br.com.livroandroid.carros.Fragments.CarrosFragment;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);
        setUpToolbar();
        //titulo e botão voltart

        Carro c = Parcels.unwrap(getIntent().getParcelableExtra("carro"));
        getSupportActionBar().setTitle(c.nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            CarroFragment f = new CarroFragment();
            f.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.carrofragmennt,f).commit();

        }

    }
}
