package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Какой переводчик Вы бы хотели использовать:");
                System.out.println("1 - Английский-русский");
                System.out.println("2 - Русско-английский");
                System.out.println("0 - Выход");
                int command = scanner.nextInt();

                switch (command) {
                    case 1:
                        System.out.println("Какую фразу Вам перевести?");
                        String message = scanner.nextLine();
                        String messageRU = translateText(message);
                        System.out.println(messageRU);

                        break;
                    case 2:
                        System.out.println("Какую фразу Вам перевести?");
                        String message1 = scanner.nextLine();
                        String message2EN = translateText1(message1);
                        System.out.println(message2EN);
                        break;
                    case 0:
                        System.out.println("Выход");
                        System.exit(0);
                    default:
                        System.out.println("Извините, такой команды пока нет.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static String translateText(String text) throws Exception {
        String TRANSLATE_API_URL = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ru&dt=t&q=";
        String urlString = TRANSLATE_API_URL + URLEncoder.encode(text, "UTF-8");
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        conn.disconnect();
        return (new JSONArray(response.toString())).getJSONArray(0).getJSONArray(0).getString(0);

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
