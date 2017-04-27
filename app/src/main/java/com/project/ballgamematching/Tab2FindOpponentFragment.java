package com.project.ballgamematching;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jylee on 2017-04-27.
 */

public class Tab2FindOpponentFragment extends Fragment {


    public Tab2FindOpponentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2_find_opponent, container, false);
    }

}
