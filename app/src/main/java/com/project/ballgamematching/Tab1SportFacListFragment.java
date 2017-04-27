package com.project.ballgamematching;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jylee on 2017-04-27.
 */

public class Tab1SportFacListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    View view;

    public Tab1SportFacListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // https://developer.android.com/samples/RecyclerView/src/com.example.android.recyclerview/RecyclerViewFragment.html 참고
        view = inflater.inflate(R.layout.fragment_tab1_sport_fac_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_sport_fac_list);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SportFacRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }

}
