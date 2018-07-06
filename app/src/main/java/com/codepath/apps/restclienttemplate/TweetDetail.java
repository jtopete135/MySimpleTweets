package com.codepath.apps.restclienttemplate;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetail extends AppCompatActivity {

    Tweet tweet;

    TextView tvUserName;
    TextView tvBody;
    ImageView ivProfileImage;
    TextView tvRelativeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        // unwrap the movie passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweetDetail"));

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvBody = (TextView) findViewById(R.id.tvBody);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvRelativeTime = (TextView) findViewById(R.id.tvRelativeTime);

        tvUserName.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvRelativeTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));

        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);

        ImageButton ibFavorite = (ImageButton) findViewById(R.id.ibFavorite);
        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
