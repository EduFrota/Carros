package br.com.livroandroid.carros.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.livroandroid.carros.Fragments.CarrosFragment;
import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;

/**
 * Created by edu-f on 04/05/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private Context context;

    public TabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }



    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if(position == 0) {
            f = CarrosFragment.newInstance("classicos");
        }else if(position == 1) {
            f = CarrosFragment.newInstance("esportivos");
        }else {
            f = CarrosFragment.newInstance("luxo");
        }
        return f;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.classicos);
        } else if (position == 1) {
            return context.getString(R.string.esportivos);
        }
        return context.getString(R.string.luxo);
    }

}
