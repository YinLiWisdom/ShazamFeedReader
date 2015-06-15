package com.yinli.shazamfeedreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.yinli.shazamfeedreader.adapters.ListAdapter;
import com.yinli.shazamfeedreader.utils.XMLHelper;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://www.shazam.com/music/web/taglistrss?mode=xml&userName=shazam";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ListAdapter(new String[] {"test1", "test2","test1", "test2","test1", "test2","test1", "test2","test1", "test2"});
        mRecyclerView.setAdapter(mAdapter);

        loadData();
    }

    private void loadData() {
        new DownloadXmlTask().execute(URL);
    }

    private class DownloadXmlTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            startLoading();
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                return "";
            } catch (XmlPullParserException e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
//            if (TextUtils.equals(result, CONNERROR)) {
//                showErrorPage(CONNERROR);
//            } else if (TextUtils.equals(result, XMLERROR)) {
//                showErrorPage(XMLERROR);
//            } else {
//                endLoading();
//                updateGrid();
//            }
        }
    }

    private String loadXmlFromNetwork(String url) throws XmlPullParserException, IOException {
        InputStream stream = null;
        try {
            stream = downloadUrl(url);
            new XMLHelper().parse(stream);
//            catImages.clear();
//            catImages.addAll(new XMLHelper().parse(stream));
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return "";
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        java.net.URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
