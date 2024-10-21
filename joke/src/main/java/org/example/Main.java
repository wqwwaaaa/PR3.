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
        String Base_URL = "https://official-joke-api.appspot.com/random_joke";
        try {
            URL url = new URL(Base_URL);
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

            String setup = jsonResponse.getString("setup");
            String punchline = jsonResponse.getString("punchline");
            String type = jsonResponse.getString("type");

            //if(type == "programming") {
                String setup1 = translateText(setup);
                System.out.println(setup1);
                String punchline1 = translateText(punchline);
                System.out.println(punchline1);
            //}
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    private static String translateText(String text) throws Exception{
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";

        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text,"UTF-8");
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