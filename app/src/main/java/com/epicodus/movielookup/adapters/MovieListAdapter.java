package com.epicodus.movielookup.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.epicodus.movielookup.R;
import com.epicodus.movielookup.models.Movie;
import com.epicodus.movielookup.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public MovieListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.movieImageView) ImageView mMovieImageView;
        @Bind(R.id.movieTitleTextView) TextView mMovieTitleTextView;
        @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
        @Bind(R.id.languageTextView) TextView mLanguageTextView;
        @Bind(R.id.ratingBar)
        RatingBar mRatingBar;
        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("movies", Parcels.wrap(mMovies));
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindMovie(Movie movie) {
            if (movie.getImageUrl() != "null") {
                Picasso.with(mContext).load(movie.getImageUrl()).into(mMovieImageView);
            } else {
                Picasso.with(mContext).load("https://cdn.amctheatres.com/Media/Default/Images/noposter.jpg").into(mMovieImageView);
            }
            mMovieTitleTextView.setText(movie.getTitle());
            mReleaseDateTextView.setText(movie.getReleaseDate());
            mLanguageTextView.setText(movie.getLanguage());
            mRatingBar.setRating((float) movie.getRating());

        }
    }


}
