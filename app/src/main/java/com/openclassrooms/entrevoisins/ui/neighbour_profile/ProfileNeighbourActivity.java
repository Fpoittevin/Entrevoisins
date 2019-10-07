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
import com.openclassrooms.entrevoisins.events.FavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.activity_profile_neighbour_second_name_txt)
    TextView mSecondName;
    @BindView(R.id.activity_profile_neighbour_address_txt)
    TextView mAddress;
    @BindView(R.id.activity_profile_neighbour_phone_number_txt)
    TextView mPhoneNumber;
    @BindView(R.id.activity_profile_neighbour_facebook_account_txt)
    TextView mFacebookAccount;
    @BindView(R.id.activity_profile_neighbour_description_txt)
    TextView mDescription;
    @BindView(R.id.activity_profile_neighbour_favorite_btn)
    FloatingActionButton mFavoriteButton;
    @BindView(R.id.activity_profile_neighbour_back_btn)
    Button mBackButton;

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
        mSecondName.setText(mNeighbour.getName());
        mAddress.setText(mNeighbour.getAddress() + " à " + mNeighbour.getDistance() + "km");
        mPhoneNumber.setText(mNeighbour.getPhoneNumber());
        mFacebookAccount.setText(mNeighbour.getFacebookAccount());
        mDescription.setText(mNeighbour.getDescription());

        //String bigAvatarUrl = mNeighbour.getAvatarUrl().replace("/150","/300");
        String bigAvatarUrl = mNeighbour.getAvatarUrl();
        Glide.with(this).load(bigAvatarUrl).into(mAvatar);

        //Favorite Button
        setColorFavoriteButton();

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(new FavoriteNeighbourEvent(mNeighbour));
                setColorFavoriteButton();
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

        if (mNeighbour.isFavorite()) {
            mFavoriteButton.setColorFilter(Color.YELLOW);
        } else {
            mFavoriteButton.setColorFilter(Color.LTGRAY);
        }
    }
}