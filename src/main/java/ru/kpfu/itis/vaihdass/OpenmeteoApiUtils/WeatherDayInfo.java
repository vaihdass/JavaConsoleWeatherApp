package ru.kpfu.itis.vaihdass.OpenmeteoApiUtils;

public class WeatherDayInfo {
    private String prettyDate;
    private String maxTemp;
    private String minTemp;
    private int weatherCod;
    private String precipitations;
    private String precipitationsProbability;
    private String maxWindspeed;
    private String uvIndex;

    public WeatherDayInfo(String prettyDate, String maxTemp, String minTemp,
                          int weatherCod, String precipitations, String precipitationsProbability,
                          String maxWindspeed, String uvIndex) {
        this.prettyDate = prettyDate;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.weatherCod = weatherCod;
        this.precipitations = precipitations;
        this.precipitationsProbability = precipitationsProbability;
        this.maxWindspeed = maxWindspeed;
        this.uvIndex = uvIndex;
    }

    public String getPrettyDate() {
        return prettyDate;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public int getWeatherCod() {
        return weatherCod;
    }

    public String getPrecipitations() {
        return precipitations;
    }

    public String getPrecipitationsProbability() {
        return precipitationsProbability;
    }

    public String getMaxWindspeed() {
        return maxWindspeed;
    }

    public String getUvIndex() {
        return uvIndex;
    }
}
