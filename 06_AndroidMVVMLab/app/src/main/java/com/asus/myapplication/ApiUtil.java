package com.asus.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Allen on 2018/2/11.
 */

public class ApiUtil<T> {

    public static String getData(String urlStr) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setReadTimeout(60 * 1000);
            connection.connect();

            int reponse = connection.getResponseCode();

            if (reponse == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                reader.close();
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null)
                connection.disconnect();
        }
        return null;
    }
}
