package com.example.demo7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

// kết nối với API của gg dịch để dịch, có thể dịch mọi ngôn ngữ.
public class Translator {

    // tiếng anh -> tiếng việt.
    public String translateText1(String text) throws IOException {
        return translate("en", "vi", text);
    }

    // tiếng việt -> tiếng anh
    public String translateText2(String text) throws IOException {
        return translate("vi", "en", text);
    }

    private String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbwZCOyYle_uOAAOWpu69tVmW5iHEi8S4ehMEwQ8QX3v-PpeVYXNpgWnuYt_L5oUKI9BQQ/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
