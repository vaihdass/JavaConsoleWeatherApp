package ru.kpfu.itis.vaihdass;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.vaihdass.DataStructures.Languages;
import ru.kpfu.itis.vaihdass.Helpers.JsonGetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command;
        /*while ((command = scanner.nextLine()) != null && !command.equalsIgnoreCase("exit")) {
            CoordinatesStringified coordinatesStringified = getCoordsByCityName(command, Languages.RU);
            System.out.println(coordinatesStringified != null ? coordinatesStringified.getLatitude() + " " + coordinatesStringified.getLongitude() : "Null");
        }*/
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

        return null;
    }
}