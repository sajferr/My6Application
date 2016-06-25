package com.example.m.my6application;

import android.content.Context;
import android.media.Image;
import android.media.Rating;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        new Tusk().execute();


    }

    public class Tusk extends AsyncTask<Void, Void, List<Library>> {
        HttpURLConnection htp;
        InputStream ip;
        BufferedReader reader;
        List<Library> listy;
        Library library;

        @Override
        protected List<Library> doInBackground(Void... params) {


            try {
                String adresURL = "http://api.androidhive.info/json/movies.json";
                URL url = new URL(adresURL);
                htp = (HttpURLConnection) url.openConnection();
                htp.connect();
                Log.d("Uwaga", "jsonek4");
                ip = htp.getInputStream();
                Log.d("Uwaga", "jsonek5");
                reader = new BufferedReader(new InputStreamReader(ip));
                StringBuilder builder = new StringBuilder();
                Log.d("Uwaga", "15");
                Log.d("Uwaga", "15");
                String line = "";
                while ((line = reader.readLine()) != null) {

                    builder.append(line + "\n");

                }
                String jsonek = builder.toString();
                Log.d("Uwaga", "jsonek2");
                listy = new ArrayList<Library>();
                JSONArray array = new JSONArray(jsonek);
                Log.d("Uwaga", "16");

                for (int i = 0; i < 15; i++) {
                    JSONObject obiekt2 = array.getJSONObject(i);
                    //String ing = obiekt2.getString("title");
                    Log.d("Uwaga", "17");
                    library = new Library();
                    library.setTitle(obiekt2.getString("title"));
                    //String ing = obiekt2.getString("title");
                    library.setImage(obiekt2.getString("image"));
                    Log.d("Uwaga", "192");
                    library.setRating(obiekt2.getInt("rating"));
                    library.setReleaseYear(obiekt2.getInt("releaseYear"));
                    Log.d("Uwaga", "1");
                    JSONArray array2 = obiekt2.getJSONArray("genre");
                    Log.d("Uwaga", "19");
                    Log.d("uwaga","stary");
                    library.setName(String.valueOf(array2) + "\n" + "\n");
                    listy.add(library);
                }


//                "title": "Dawn of the Planet of the Apes",
//                        "image": "http://api.androidhive.info/json/movies/1.jpg",
//                        "rating": 8.3,
//                        "releaseYear": 2014,
//                        "genre": ["Action", "Drama", "Sci-Fi"]


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return listy;
        }

        @Override
        protected void onPostExecute(List<Library> integer) {
            super.onPostExecute(integer);
            Log.d("Uwaga", "25");
//            StringBuilder builder = new StringBuilder();
//            for(int i=0;i<integer.size();i++){
//                Log.d("Uwaga","23");
//                Library library =  integer.get(i);
//                Log.d("Uwaga","28");
//                builder.append(library.getTitle()+"\n"+library.getRating()+"\n"+library.getReleaseYear()+"\n"+library.getName()+"\n"+"\n");
//
//            }

            Log.d("Uwaga", "29");

            if (htp != null) {
                htp.disconnect();
                Log.d("Uwaga", "26");
                try {
                    ip.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MaineAdapter adapter = new MaineAdapter(getApplicationContext(),R.layout.lista,integer);
                listView.setAdapter(adapter);

            }
        }
    }

   public class MaineAdapter extends ArrayAdapter {
        LayoutInflater inflater;
        int resource ;
        List<Library> libraryList;
        TextView tekst2;
        TextView tekst3;
        TextView tekst4;
        RatingBar bar ;

        public MaineAdapter(Context context, int resource, List<Library> objects) {
            super(context, resource, objects);
            inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            this.resource=resource;
            libraryList=objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                Log.d("Uwaga","200320");
               convertView= inflater.inflate(resource,null);
            }
            if(parent==null){
                Log.d("Uwaga","111111111");
                String lolz;
            }
            Log.d("Uwaga","20020");
            if (convertView != null) {
                Log.d("Uwaga","2002022");
                tekst2=(TextView)convertView.findViewById(R.id.textView2);
            }

            tekst3=(TextView)convertView.findViewById((R.id.textView3));
            Log.d("Uwaga","20030");
            tekst4=(TextView)convertView.findViewById((R.id.textView4));
            ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
            bar = (RatingBar)convertView.findViewById((R.id.ratingBar));
            bar.setNumStars(6);
            float rating=libraryList.get(position).getRating();
            String title=libraryList.get(position).getTitle();
            int years=libraryList.get(position).getReleaseYear();


            tekst2.setText(title);
            Log.d("Uwaga","2000");
            tekst3.setText(String.valueOf(years));
            bar.setRating(rating/2);
            ImageLoader.getInstance().displayImage(libraryList.get(position).getImage(),image);


               return convertView;
        }
    }
}




