package com.example.quake;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=50";
    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        adapter = new EarthquakeAdapter(this, new ArrayList<EarthQuake>());

        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                EarthQuake currentEarthquake = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
        // Create a new {@link ArrayAdapter} of earthquakes
        DownloadDataFromServer downloadDataFromServer = new DownloadDataFromServer();
        downloadDataFromServer.execute(URL);

    }

    private class DownloadDataFromServer extends AsyncTask<String, Void, List<EarthQuake>> {

        protected List<EarthQuake> doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<EarthQuake> earthQuakes = QueryList.fetchEarthquakeData(urls[0]);
            return earthQuakes;
        }

        protected void onPostExecute(List<EarthQuake> earthQuakes) {
            if (earthQuakes == null) {
                return;
            }
            adapter.clear();
            if (earthQuakes != null && !earthQuakes.isEmpty()) {
                adapter.addAll(earthQuakes);
            }

        }
    }
}
