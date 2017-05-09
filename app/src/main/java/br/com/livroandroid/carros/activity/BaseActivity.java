package br.com.livroandroid.carros.activity;


import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import br.com.livroandroid.carros.Fragments.CarrosFragment;
import br.com.livroandroid.carros.Fragments.CarrosTabFragment;
import br.com.livroandroid.carros.Fragments.SiteLivroFragment;
import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.NavDrawerUtil;


/**
 * Created by efrota on 12/04/17.
 */

public class BaseActivity extends livroandroid.lib.activity.BaseActivity {
    protected DrawerLayout drawerLayout;
    //Toolbal
    protected void setUpToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }
    //NavDrawer
    protected void setupNavDrawer(){
        //layout
        final ActionBar actionBar = getSupportActionBar();
        //Icone do menu
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if(navigationView != null && drawerLayout !=null){
            //Atualiza a Imagem e textos do header
            NavDrawerUtil.setHeaderValues(navigationView, R.id.containerNavDrawerListViewHeader,
                    R.drawable.nav_drawer_header, R.drawable.ic_logo_user,
                    R.string.nav_drawer_username, R.string.nav_drawer_email);
            //Clique do menu
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected( MenuItem menuItem) {
                    //seleciona linha
                    menuItem.setChecked(true);
                    //fecha o menu
                    drawerLayout.closeDrawers();
                    //trata o evento do menu
                    onNavDrawerItemSelected(menuItem);
                    return true;
                }
            });

        }
    }
    //Evento Menu Lateral
    private void onNavDrawerItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.nav_item_carros_todos:
              toast("Clicou em carros");
                replaceFragment(new CarrosTabFragment());
              break;
            case R.id.nav_item_carros_classicos:
                toast("Clicou em carros classicos");
                replaceFragment(CarrosFragment.newInstance("classicos"));
                break;
            case R.id.nav_item_carros_esportivos:
            toast("Clicou em esportivos");
                replaceFragment(CarrosFragment.newInstance("esportivos"));
            break;
            case R.id.nav_item_carros_luxo:
            toast("Clicou em carros de Luxo");
                replaceFragment(CarrosFragment.newInstance("luxo"));
            break;
        case R.id.nav_item_site_livro:
            snack(drawerLayout,"clicou em site");
            replaceFragment(new SiteLivroFragment());
            break;
        case R.id.nav_item_settings:
            toast("Clicou em setings");
    }

}
//adiciona o fragment na tela
    protected void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment,"TAG").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                if(drawerLayout!=null){
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);

    }
    //abre MenuLateral
    protected void openDrawer(){
        if(drawerLayout != null){
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
    //fecha o menu Lateral
    protected void closeDrawer(){
        if(drawerLayout != null){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

}

