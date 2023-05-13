package ru.kpfu.itis.vaihdass.OpenmeteoApiUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.kpfu.itis.vaihdass.DataStructures.Languages;
import ru.kpfu.itis.vaihdass.Helpers.JsonGetter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WeatherService {
    public static List<WeatherDayInfo> getWeatherByCoords(CoordinatesStringified coords) {
        try {
            if (coords == null
                    || coords.getLatitude() == null
                    || coords.getLongitude() == null) return null;
            URL url = getWeatherRequestUrl(coords.getLatitude(), coords.getLongitude());
            if (url == null) return null;

            String jsonData = JsonGetter.getJson(url);
            if (jsonData == null) return null;

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonData);

            if (root.path("daily") == null) return null;

            List<WeatherDayInfo> weatherDayInfoList = new ArrayList<>(7);
            String currentDate;
            int i = 0;
            JsonNode daily = root.path("daily");
            Iterator<JsonNode> dates = daily.path("time").elements();
            while (dates.hasNext()) {
                currentDate = dates.next().asText();
                weatherDayInfoList.add(new WeatherDayInfo(
                        getPrettyDate(currentDate),
                        getArrItem(daily, "temperature_2m_max", i).asText() + ' ' + root.path("daily_units").get("temperature_2m_max").asText(),
                        getArrItem(daily, "temperature_2m_min", i).asText() + ' ' + root.path("daily_units").get("temperature_2m_min").asText(),
                        getArrItem(daily, "weathercode", i).asInt(),
                        getArrItem(daily, "precipitation_sum", i).asText() + ' ' + root.path("daily_units").get("precipitation_sum").asText(),
                        getArrItem(daily, "precipitation_probability_max", i).asText() + ' ' + root.path("daily_units").get("precipitation_probability_max").asText(),
                        getArrItem(daily, "windspeed_10m_max", i).asText() + ' ' + root.path("daily_units").get("windspeed_10m_max").asText(),
                        getArrItem(daily, "uv_index_max", i).asText()));
                i++;
            }
            return weatherDayInfoList;
        } catch (IOException e) {
            return null;
        }
    }

    private static JsonNode getArrItem(JsonNode parent, String property,int index) {
        return ((ArrayNode) parent.path(property)).get(index);
    }

    private static String getPrettyDate(String date) {
        try {
            return (new SimpleDateFormat("EE, MMMMMMM dd, yy")).format((new SimpleDateFormat("yyyy-MM-dd")).parse(date));
        } catch (ParseException e) {
            return date;
        }
    }

    public static URL getWeatherRequestUrl(String latitude, String longitude) {
        try {
            if (latitude == null || longitude == null) return null;

            return new URL("https://api.open-meteo.com/v1/forecast?latitude=" +
                    latitude + "&longitude=" + longitude +
                    "&timezone=GMT&" +
                    "daily=weathercode,temperature_2m_max,temperature_2m_min,precipitation_sum,precipitation_probability_max," +
                    "windspeed_10m_max,uv_index_max");
        } catch (MalformedURLException e) {
            return null;
        }
    }


    public static CoordinatesStringified getCoordsByCityName(String cityName, Languages language) {
        try {
            URL url = getCityCoordsRequestURL(cityName, language);
            if (url == null) return null;

            String jsonData = JsonGetter.getJson(url);
            if (jsonData == null) return null;

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);
            if (rootNode.path("results") == null || !rootNode.path("results").elements().hasNext()) return null;

            JsonNode cityInfo = rootNode.path("results").elements().next();
            if (cityInfo.path("latitude") == null || cityInfo.path("longitude") == null) return null;

            return new CoordinatesStringified(cityInfo.path("latitude").asText(), cityInfo.path("longitude").asText());
        } catch (IOException e) {
            return null;
        }
    }

    public static URL getCityCoordsRequestURL(String cityName, Languages language) {
        try {
            if (cityName == null || language == null) return null;
            cityName = URLEncoder.encode(cityName, "UTF-8");

            return new URL("https://geocoding-api.open-meteo.com/v1/search?name="
                    + cityName + "&count=1&language="+ language.getLocationCode());
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            return null;
        }
    }

    public static class CoordinatesStringified {
        private String latitude;
        private String longitude;

        public CoordinatesStringified(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
