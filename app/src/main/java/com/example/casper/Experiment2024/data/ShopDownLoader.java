package com.example.casper.Experiment2024.data;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShopDownLoader {

    public static class DownloadTask extends AsyncTask<String, Void, ArrayList<ShopLocation>> {
        @Override
        protected ArrayList<ShopLocation> doInBackground(String... urls) {
            String jsonResponse = download(urls[0]);
            return parseJson(jsonResponse);
        }

        private String download(String urlString) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        private ArrayList<ShopLocation> parseJson(String jsonString) {
            ArrayList<ShopLocation> shops = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    double latitude = jsonObject.getDouble("latitude");
                    double longitude = jsonObject.getDouble("longitude");
                    shops.add(new ShopLocation(name, latitude, longitude));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return shops;
        }
    }
}