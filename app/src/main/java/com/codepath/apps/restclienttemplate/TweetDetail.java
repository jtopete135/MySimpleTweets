package com.codepath.apps.restclienttemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class TweetDetail extends AppCompatActivity {

    Tweet tweet;
    TwitterClient client;
    TextView tvUserName;
    TextView tvBody;
    ImageView ivProfileImage;
    TextView tvRelativeTime;
    TextView tvFavRetweetCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        client = TwitterApp.getRestClient(this);
        // unwrap the movie passed in via intent, using its simple name as a key
        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra("tweetDetail"));

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvBody = (TextView) findViewById(R.id.tvBody);
        ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
        tvRelativeTime = (TextView) findViewById(R.id.tvRelativeTime);
        tvFavRetweetCount = (TextView) findViewById(R.id.tvFavRetweetCount);

        tvUserName.setText(tweet.user.name);
        tvBody.setText(tweet.body);
        tvRelativeTime.setText(tweet.getRelativeTimeAgo(tweet.createdAt));
        tvFavRetweetCount.setText(String.format("%d Likes %d Retweets",tweet.favoriteCount,tweet.retweetCount));



        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfileImage);

        final ImageButton ibFavorite = (ImageButton) findViewById(R.id.ibFavorite);
        ibFavorite.setSelected(tweet.favorited);
        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibFavorite.setSelected(!ibFavorite.isSelected());

                client.favorite(true, tweet.uid, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        tweet.favoriteCount++;
                        tvFavRetweetCount.setText(String.format("%d Likes %d Retweets",tweet.favoriteCount,tweet.retweetCount));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });

        final ImageButton ibRetweet = (ImageButton) findViewById(R.id.ibRetweet);
        ibRetweet.setSelected(tweet.retweeted);
        ibRetweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibRetweet.setSelected(!ibRetweet.isSelected());

                client.retweet(true, tweet.uid, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        tweet.retweetCount++;
                        tvFavRetweetCount.setText(String.format("%d Likes %d Retweets",tweet.favoriteCount,tweet.retweetCount));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });
    }


}
