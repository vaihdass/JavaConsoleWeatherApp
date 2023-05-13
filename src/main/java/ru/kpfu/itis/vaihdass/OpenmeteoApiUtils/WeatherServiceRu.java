package ru.kpfu.itis.vaihdass.OpenmeteoApiUtils;

import ru.kpfu.itis.vaihdass.DataStructures.Languages;

public class WeatherServiceRu extends WeatherService {
    public static CoordinatesStringified getCoordsByCityName(String name) {
        return getCoordsByCityName(name, Languages.RU);
    }
}
