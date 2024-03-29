package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {
    private Context context;
    private List<Tweet> tweetList;
    private NavController navHostFragment;
    private Boolean profileButtonClickable = true;

    public TweetAdapter(Context context, NavController navHostFragment) {
        this.context = context;
        this.navHostFragment = navHostFragment;
        tweetList = new ArrayList<>();
    }

    public void addTweetList(List<Tweet> tweetList) {
        this.tweetList.clear();
        this.tweetList.addAll(tweetList);
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_view, parent, false);
        return new TweetViewHolder(view, navHostFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        String profilePic;
        holder.mScreenName.setText(tweetList.get(position).getUser().getScreen_name());
        holder.mName.setText(tweetList.get(position).getUser().getName());
        holder.mTweet.setText(tweetList.get(position).getText());
        holder.mTimeCreated.setText(tweetList.get(position).getCreated_at());
        profilePic = tweetList.get(position).getUser().getProfile_image_url_https()
                .replace("_normal", "");
        Picasso.get().load(profilePic).into(holder.mProfilePic);
        if (tweetList.get(position).getMedia_url_https() != null) {
            Picasso.get().load(tweetList.get(position).getMedia_url_https()).into(holder.mMedia);
            holder.mMedia.setVisibility(View.VISIBLE);
        } else {
            holder.mMedia.setVisibility(View.GONE);
        }
        holder.mUserID = tweetList.get(position).getUser().getId_str();
        if (!profileButtonClickable) holder.disableButtons();
    }

    public void disableProfileButton() {
        profileButtonClickable = false;
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

}
