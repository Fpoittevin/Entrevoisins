package com.openclassrooms.entrevoisins.ui.neighbour_profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNeighbourActivity extends AppCompatActivity {

    private Neighbour mNeighbour;
    private NeighbourApiService mNeighbourApiService;

    //UI Components
    @BindView(R.id.activity_profile_neighbour_avatar_img)
    ImageView mAvatar;
    @BindView(R.id.activity_profile_neighbour_name_txt)
    TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_neighbour);

        mNeighbourApiService = DI.getNeighbourApiService();

        //Generate mNeighbour with id in Intent
        int idNeighbour = getIntent().getIntExtra("idNeighbour", 0);
        mNeighbour = mNeighbourApiService.getNeighbour(idNeighbour);

        //Insert neighbour data in layout
        ButterKnife.bind(this);

        mName.setText(mNeighbour.getName());

        Glide.with(this).load(mNeighbour.getAvatarUrl()).into(mAvatar);
    }
}
