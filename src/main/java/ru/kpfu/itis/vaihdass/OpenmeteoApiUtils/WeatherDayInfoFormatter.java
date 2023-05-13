package ru.kpfu.itis.vaihdass.OpenmeteoApiUtils;

import ru.kpfu.itis.vaihdass.Helpers.ColoredStringBuilder;

import java.awt.*;

public class WeatherDayInfoFormatter {
    public static String toPrettyString(WeatherDayInfo day) {
        // Mimic the functional JSX components?!
        return ColoredStringBuilder.getColored( "Погода ", ColoredStringBuilder.AnsiColors.ANSI_BLUE.bold()) +
                ColoredStringBuilder.getColored(day.getPrettyDate() + ":\n", ColoredStringBuilder.AnsiColors.ANSI_YELLOW.bold()) +
                colorProperty("Макс. температура: ", day.getMaxTemp() + "\n") +
                colorProperty("Мин. температура: ", day.getMinTemp() + "\n") +
                colorProperty("Погода: ", getWeatherDescription(day.getWeatherCod()) + "\n") +
                colorProperty("Осадки: ", day.getPrecipitations() + "\n") +
                colorProperty("Вероятность осадков: ", day.getPrecipitationsProbability() + "\n") +
                colorProperty("Ветер: ", day.getMaxWindspeed() + "\n") +
                colorProperty("УФ-индекс: ", day.getUvIndex());
    }

    private static String colorProperty(String description, String value) {
        return ColoredStringBuilder.getColored(description, ColoredStringBuilder.AnsiColors.ANSI_BLUE.text()) +
                ColoredStringBuilder.getColored(value, ColoredStringBuilder.AnsiColors.ANSI_YELLOW.text());
    }

    public static String getWeatherDescription(int cod) {
        if (cod <= 67) {
            if (cod == 0) return "Чистое небо";
            if (cod == 1) return "Преимущественно ясно";
            if (cod == 2) return "Переменная облачность";
            if (cod == 3) return "Пасмурная погода";

            if (cod == 45) return "Туман";
            if (cod == 48) return "Изморозь";

            if (cod == 51) return "Изморось: слабая";
            if (cod == 53) return "Изморось: умеренная";
            if (cod == 55) return "Изморось: плотная, интенсивная";

            if (cod == 56) return "Ледяная изморось: лёгкая";
            if (cod == 57) return "Ледяная изморось: плотная";

            if (cod == 61) return "Дождь: лёгкий";
            if (cod == 63) return "Дождь: умеренный";
            if (cod == 65) return "Дождь: сильный";

            if (cod == 66) return "Ледяной дождь: лёгкий";
            if (cod == 67) return "Ледяной дождь: сильный";

        } else {
            if (cod == 71) return "Снегопад: легкий";
            if (cod == 73) return "Снегопад: умеренный";
            if (cod == 75) return "Снегопад: обильный";

            if (cod == 77) return "Снежные хлопья";

            if (cod == 80) return "Ливень: слабый";
            if (cod == 81) return "Ливень: умеренный";
            if (cod == 82) return "Ливень: сильный";

            if (cod == 85) return "Снегопад: слабый";
            if (cod == 86) return "Снегопад: обильный";

            if (cod == 95) return "Гроза";

            if (cod == 96) return "Гроза со слабым градом";
            if (cod == 99) return "Гроза с сильным градом";
        }

        return "Нет информации о погоде.";
    }
}
