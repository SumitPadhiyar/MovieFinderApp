package com.demoapps.moviefinderapp.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.demoapps.moviefinderapp.R;
import com.tokenautocomplete.TokenCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_LOG = MainActivity.class.getSimpleName();
    private Spinner spinner;
    private MovieCompletionView movieCompletionView;
    private Button searchBtn;

    private ArrayList<String> moviesToSearch;
    private String searchType = "movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG_LOG, "onCreate:  IN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.sp__act_main_item__type);
        movieCompletionView = (MovieCompletionView) findViewById(R.id.mcv__act_main__movies);
        searchBtn = (Button) findViewById(R.id.btn__act_main__search);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.content_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                searchType = ((String) parent.getItemAtPosition(position)).toLowerCase();
                Log.v(TAG_LOG, "onItemSelected: " + searchType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        movieCompletionView.setTokenListener(stringTokenListener);
        moviesToSearch = new ArrayList<>();


        Log.v(TAG_LOG, "onCreate:  OUT");
    }

    private TokenCompleteTextView.TokenListener<String> stringTokenListener = new TokenCompleteTextView.TokenListener<String>() {
        @Override
        public void onTokenAdded(String token) {
            Log.v(TAG_LOG, "onTokenAdded: " + token);

            if(!moviesToSearch.contains(token)){
                if(moviesToSearch.isEmpty()){
                    setSearchBtnEnabled(true);
                }
                moviesToSearch.add(token);

            }
        }

        @Override
        public void onTokenRemoved(String token) {
            Log.v(TAG_LOG, "onTokenRemoved: " + token);
            moviesToSearch.remove(token);
            if(moviesToSearch.isEmpty()){
                setSearchBtnEnabled(false);
            }
        }
    };



    @Override
    public void onClick(View v) {
        Log.v(TAG_LOG, "onClick:  IN");

        switch (v.getId()){
            case R.id.btn__act_main__search:
                break;

        }
        Log.v(TAG_LOG, "onClick:  OUT");
    }

    private void setSearchBtnEnabled(boolean enable){
            searchBtn.setEnabled(enable);
            searchBtn.setClickable(enable);
    }

    private void searchMovies(){

    }


}
