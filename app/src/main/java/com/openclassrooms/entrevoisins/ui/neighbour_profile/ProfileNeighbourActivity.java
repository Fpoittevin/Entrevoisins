package com.openclassrooms.entrevoisins.ui.neighbour_profile;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.FavoriteNeighboursListChangeEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.FavoriteNeighboursManager;
import com.openclassrooms.entrevoisins.service.FavoriteNeighboursStorage;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNeighbourActivity extends AppCompatActivity {

    private Neighbour mNeighbour;
    private NeighbourApiService mNeighbourApiService;
    private FavoriteNeighboursManager mFavoriteNeighboursManager;
    private boolean neighbourIsFavorite;

    //UI Components
    @BindView(R.id.activity_profile_neighbour_avatar_img)
    ImageView mAvatar;
    @BindView(R.id.activity_profile_neighbour_name_txt)
    TextView mName;
    @BindView(R.id.activity_profile_neighbour_favorite_btn)
    FloatingActionButton mFavoriteButton;
    @BindView(R.id.activity_profile_neighbour_back_btn)
    Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);

        mNeighbourApiService = DI.getNeighbourApiService();
        mFavoriteNeighboursManager = new FavoriteNeighboursManager(new FavoriteNeighboursStorage(this));

        //Generate mNeighbour with id in Intent
        int idNeighbour = getIntent().getIntExtra("idNeighbour", 0);
        mNeighbour = mNeighbourApiService.getNeighbour(idNeighbour);

        //Insert neighbour data in layout
        ButterKnife.bind(this);

        mName.setText(mNeighbour.getName());

        String bigAvatarUrl = mNeighbour.getAvatarUrl().replace("/150","/300");
        Glide.with(this).load(bigAvatarUrl).into(mAvatar);

        //Favorite Button
        setColorFavoriteButton();

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (neighbourIsFavorite) {
                    mFavoriteNeighboursManager.removeToFavorite(mNeighbour);
                } else {
                    mFavoriteNeighboursManager.addToFavorite(mNeighbour);
                }
                setColorFavoriteButton();

                EventBus.getDefault().postSticky(new FavoriteNeighboursListChangeEvent());
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileNeighbourActivity.this.finish();
            }
        });
    }

    private void setColorFavoriteButton() {
        neighbourIsFavorite = mFavoriteNeighboursManager.isFavorite(mNeighbour);

        if (neighbourIsFavorite) {
            mFavoriteButton.setColorFilter(R.color.yellowStar);
        } else {
            mFavoriteButton.setColorFilter(Color.WHITE);
        }
    }
}
