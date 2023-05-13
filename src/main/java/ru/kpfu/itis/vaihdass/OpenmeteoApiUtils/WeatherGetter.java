package ru.kpfu.itis.vaihdass.OpenmeteoApiUtils;

import ru.kpfu.itis.vaihdass.DataStructures.Time;
import ru.kpfu.itis.vaihdass.Helpers.ColoredStringBuilder;

import java.util.List;

public class WeatherGetter {
    public static String getWeather(String name, Time time) {
        WeatherService.CoordinatesStringified cityCoords = WeatherServiceRu.getCoordsByCityName(name);
        if (cityCoords == null) return null;

        List<WeatherDayInfo> weatherList = WeatherService.getWeatherByCoords(cityCoords);
        if (weatherList == null) return null;

        if (time == Time.TODAY && weatherList.size() >= 1) {
            return WeatherDayInfoFormatter.toPrettyString(weatherList.get(0));
        } else if (time == Time.TOMORROW && weatherList.size() >= 2) {
            return WeatherDayInfoFormatter.toPrettyString(weatherList.get(1));
        } else {
            if (time == Time.TODAY || time == Time.TOMORROW) return null;

            String dividerLine = ColoredStringBuilder.getColored("•••••\n", ColoredStringBuilder.AnsiColors.ANSI_YELLOW.bold());
            StringBuilder resultString = new StringBuilder(
                    ColoredStringBuilder.getColored("~~~ Погода на ближайшие 7 дней: ~~~\n\n", ColoredStringBuilder.AnsiColors.ANSI_CYAN.bold()));
            for (final WeatherDayInfo day : weatherList) {
                resultString.append(dividerLine).append(WeatherDayInfoFormatter.toPrettyString(day)).append("\n\n");
            }
            return resultString.toString();
        }
    }
}
