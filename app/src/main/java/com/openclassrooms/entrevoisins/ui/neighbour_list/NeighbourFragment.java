package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.FavoriteNeighboursListChangeEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighboursManager;
import com.openclassrooms.entrevoisins.service.FavoriteNeighboursStorage;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private FavoriteNeighboursManager mFavoriteNeighboursManager;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private int mPosition;


    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int position) {
        NeighbourFragment fragment = new NeighbourFragment();

        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        mFavoriteNeighboursManager = new FavoriteNeighboursManager(new FavoriteNeighboursStorage(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mPosition = getArguments().getInt("position");

        View view;
        if (mPosition == 0) {
            view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_favorite_neighbour_list, container, false);
        }

        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        initList();
        return view;
    }

    /**
     * Init the List of neighbours and the List of favorite neighbours
     */
    private void initList() {
        if (mPosition == 0) {
            mNeighbours = mApiService.getNeighbours();
        } else {
            mNeighbours = mFavoriteNeighboursManager.getFavoriteNeighbours();
        }
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(this.getContext(), mNeighbours));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        mFavoriteNeighboursManager.removeToFavorite(event.neighbour);
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public  void onEvent(FavoriteNeighboursListChangeEvent event){
        initList();
    }
}
