package com.example.demo7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


// kết nối với 1 API từ điển khác, không có nhiều chức năng, vì dùng API GG translate là đủ và hiệu quả hơn.
// vẫn giữ lại để nếu cần thì sẽ cải tiến APP.
public class OnlineDictionary {
    private final String apiUrl;

    public OnlineDictionary(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getDefinition(String word) {
        try {
            URL url = new URL(apiUrl + "?word=" + URLEncoder.encode(word, StandardCharsets.UTF_8));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String definition = in.readLine();
            in.close();
            return definition;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
