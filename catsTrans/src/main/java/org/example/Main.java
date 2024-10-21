package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String Base_URL1 = "https://catfact.ninja/fact";
        try {
            URL url = new URL(Base_URL1);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject jsonResponse = new JSONObject(response.toString());

            String fact = jsonResponse.getString("fact");
            System.out.println(fact);
            String fact1 = translateText(fact);
            System.out.println(fact1);
            String fact2 = translateText1(fact1);
            System.out.println(fact2);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static String translateText(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
    private static String translateText1(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=ru&tl=en&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return new JSONArray(response.toString()).getJSONArray(0).getJSONArray(0).getString(0);
    }
}
