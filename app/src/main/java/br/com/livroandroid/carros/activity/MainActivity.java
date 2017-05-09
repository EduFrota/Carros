package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.livroandroid.carros.Fragments.AboutDialogText;
import br.com.livroandroid.carros.Fragments.CarrosFragment;
import br.com.livroandroid.carros.Fragments.CarrosTabFragment;
import br.com.livroandroid.carros.R;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        setupNavDrawer();
        replaceFragment(new CarrosTabFragment());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id==R.id.action_about){
            AboutDialogText.showAbout(getSupportFragmentManager());
            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

}
