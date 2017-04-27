package com.project.ballgamematching;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jylee on 2017-04-27.
 */

public class Tab3FavoriteSportFacFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    View view;

    public Tab3FavoriteSportFacFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

/*
        view = inflater.inflate(R.layout.fragment_tab3_favorite_sport_fac, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_sport_fac_list);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SportFacRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        return view;

*/
        return inflater.inflate(R.layout.fragment_tab3_favorite_sport_fac, container, false);
    }
}
