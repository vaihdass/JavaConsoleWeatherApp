package ru.kpfu.itis.vaihdass.Helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonGetter {
    public static String getJson(URL url) {
        BufferedReader bufferedReader = null;
        try {
            if (url == null) return null;
            bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

            StringBuilder jsonDataStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonDataStringBuilder.append(line);
            }
            return jsonDataStringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
