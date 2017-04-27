package com.project.ballgamematching;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jylee on 2017-04-27.
 */

public class TabPagerAdapter extends FragmentPagerAdapter {
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1SportFacListFragment tab1SportFacListFragment = new Tab1SportFacListFragment();
                return tab1SportFacListFragment;
            case 1:
                Tab2FindOpponentFragment tab2FindOpponentFragment = new Tab2FindOpponentFragment();
                return tab2FindOpponentFragment;
            case 2:
                Tab3FavoriteSportFacFragment tab3FavoriteSportFacFragment = new Tab3FavoriteSportFacFragment();
                return tab3FavoriteSportFacFragment;
            default:
                return null;
        }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return tabCount;
    }
}
