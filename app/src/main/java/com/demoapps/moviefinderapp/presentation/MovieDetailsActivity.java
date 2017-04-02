package com.demoapps.moviefinderapp.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demoapps.moviefinderapp.R;
import com.demoapps.moviefinderapp.model.Movie;

import java.util.ArrayList;

/**
 * Created by Skip on 26-03-2017.
 */
public class MovieDetailsActivity extends AppBaseActivity {

    private static final String TAG_LOG = MovieDetailsActivity.class.getSimpleName();

    private static final String ARG_MOVIE_LIST = "arg_movie_list";

    private ArrayList<Movie> moviesList;

    public static Intent getActivityStartIntent(Context packageContext, ArrayList<Movie> movieList) {
        Intent intent = new Intent(packageContext, MovieDetailsActivity.class);
        intent.putParcelableArrayListExtra(ARG_MOVIE_LIST, movieList);
        return intent;
    }

    //region Overridden Methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.v(TAG_LOG, "onCreate:  IN");
        super.onCreate(savedInstanceState);
        Log.v(TAG_LOG, "onCreate:  OUT");
    }

    @Override
    protected void onParseArgs() {

        super.onParseArgs();
        moviesList = getIntent().getParcelableArrayListExtra(ARG_MOVIE_LIST);

    }

    @Override
    void onSetupUI(Bundle savedInstanceState) {
        setContentView(R.layout.activity_movie_details);

        RecyclerView rvMovies = (RecyclerView) findViewById(R.id.rv__act_movie_details__movies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(new MoviesAdapter(moviesList));
    }
    //endregion

    //region Inner Classes
    private class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

        private ArrayList<Movie> movieArrayList;

        public MoviesAdapter(ArrayList<Movie> movieList) {
            movieArrayList = movieList;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_details_card, parent, false);
            return new MovieViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, int position) {
            holder.onBindViewHolder(position);
        }

        @Override
        public int getItemCount() {
            return movieArrayList == null ? 0 : movieArrayList.size();
        }

        class MovieViewHolder extends RecyclerView.ViewHolder {

            private TextView tvTitle;
            private TextView tvGenre;
            private TextView tvReleaseDate;
            private TextView tvPlot;
            private TextView tvRating;
            private ProgressBar pbRatingProgressBar;

            MovieViewHolder(View itemView) {
                super(itemView);

                tvTitle = (TextView) itemView.findViewById(R.id.tv__movie_details_cardview__title);
                tvGenre= (TextView) itemView.findViewById(R.id.tv__movie_details_cardview__genre);
                tvReleaseDate = (TextView) itemView.findViewById(R.id.tv__movie_details_cardview__release_date);
                tvPlot = (TextView) itemView.findViewById(R.id.tv__movie_details_cardview__plot);
                tvRating= (TextView) itemView.findViewById(R.id.tv__movie_details_cardview__progress_rating);
                pbRatingProgressBar= (ProgressBar) itemView.findViewById(R.id.pb__movie_details_cardview__progress_bar_rating);
            }

            void onBindViewHolder(int position){
                Movie movie = movieArrayList.get(position);

                tvTitle.setText(movie.getTitle());
                tvGenre.setText(movie.getGenre());
                tvReleaseDate.setText(movie.getReleaseDate());
                tvPlot.setText(movie.getPlot());
                tvRating.setText(movie.getRating());

                int rating = 0;
                try {
                    rating = (int) Float.parseFloat(movie.getRating());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                pbRatingProgressBar.setProgress(rating);

            }

        }
    }
    //endregion
}
