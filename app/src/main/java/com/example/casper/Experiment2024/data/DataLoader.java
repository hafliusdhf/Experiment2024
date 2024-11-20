package com.example.casper.Experiment2024.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DataLoader {
    public ArrayList<ShopLocation> parseLocations(String jsonString) {
        ArrayList<ShopLocation> locationsArrayList = new ArrayList<>();
        try {
            // 创建JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            // 获取shops数组
            JSONArray shopsArray = jsonObject.getJSONArray("shops");

            // 遍历数组
            for (int i = 0; i < shopsArray.length(); i++) {
                JSONObject shopObject = shopsArray.getJSONObject(i);

                // 提取商店信息
                String name = shopObject.getString("name");
                String latitude = shopObject.getString("latitude");
                String longitude = shopObject.getString("longitude");
                String memo = shopObject.getString("memo");

                // 创建Shop对象
                ShopLocation shop = new ShopLocation(name, latitude, longitude, memo);
                locationsArrayList.add(shop);

                // 这里可以将shop对象添加到列表或进行其他处理
                Log.i("ShopLocationParser","Shop Name: " + shop.getName());
                Log.i("ShopLocationParser","Latitude: " + shop.getLatitude());
                Log.i("ShopLocationParser","Longitude: " + shop.getLongitude());
                Log.i("ShopLocationParser","Memo: " + shop.getMemo());
                Log.i("ShopLocationParser","-----------------------------");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locationsArrayList;
    }
    public String getTextFromUrl(String urlString) {
        StringBuilder result = new StringBuilder();
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // 检查响应代码
            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("TecentMapsFragment","Failed to connect: " + urlConnection.getResponseCode());
                return "";
            }

            // 输入流
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append("\n"); // 添加换行符
            }

        } catch (Exception e) {
            Log.e("TecentMapsFragment","Failed to read: " +urlString+ e.toString());
            e.printStackTrace();
        } finally {
            // 关闭连接和流
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return result.toString().trim(); // 返回结果字符串
    }
}
