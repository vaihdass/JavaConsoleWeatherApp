package ru.kpfu.itis.vaihdass.ConsoleImplementation;

import ru.kpfu.itis.vaihdass.Abstractions.Controller;
import ru.kpfu.itis.vaihdass.Abstractions.Output;
import ru.kpfu.itis.vaihdass.DataStructures.Request;
import ru.kpfu.itis.vaihdass.DataStructures.Response;
import ru.kpfu.itis.vaihdass.DataStructures.Time;
import ru.kpfu.itis.vaihdass.Helpers.ColoredStringBuilder;
import ru.kpfu.itis.vaihdass.OpenmeteoApiUtils.WeatherGetter;

public class DefaultController implements Controller {
    private Output output;
    private String defaultView;
    private String greetingMessage;

    public DefaultController(Output output, String defaultView, String greetingMessage) {
        if (output == null || defaultView == null) throw new IllegalArgumentException("Output, default view and greeting message can't be null.");
        this.output = output;
        this.defaultView = defaultView;
        this.greetingMessage = greetingMessage;
    }

    @Override
    public void getUpdatedView(Request request) {
        String[] splitCommand = request.getCommand().split("-");
        if (splitCommand.length == 0 || splitCommand[0].equals("")) {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(defaultView);
            output.setView(response);
            return;
        }
        if (splitCommand[0].equalsIgnoreCase("выход")) {
            System.exit(0);
            return;
        }
        if (splitCommand[0].equalsIgnoreCase("помощь")) {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(greetingMessage + "\n" + defaultView);
            output.setView(response);
            return;
        }
        if (splitCommand.length != 2) {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(colorRed("Команда введена в некорректном формате. Попробуйте ещё раз.\n") + defaultView);
            output.setView(response);
            return;
        }
        if (splitCommand[0].trim().equals("") || splitCommand[1].trim().equals("")) {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(colorRed("Введите название города и время для получения информации о погоде.\n") + defaultView);
            output.setView(response);
        }
        String city = splitCommand[0].trim();
        String timeString = splitCommand[1].trim();
        Time time;

        if (timeString.equalsIgnoreCase("сегодня")) time = Time.TODAY;
        else if (timeString.equalsIgnoreCase("завтра")) time = Time.TOMORROW;
        else if (timeString.equalsIgnoreCase("на неделю")) time = Time.WEEK;
        else {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(colorRed("Введите корректное время для получения информации о погоде.\n") + defaultView);
            output.setView(response);
            return;
        }

        String weatherInfo = WeatherGetter.getWeather(city, time);
        if (weatherInfo == null) {
            Response response = new Response();
            response.setRequireLinebreakAfter(false);
            response.setOutputText(colorRed("Извините, информация о погоде в указанной локации в это время не найдена.\n") + defaultView);
            output.setView(response);
            return;
        }
        Response response = new Response();
        response.setRequireLinebreakAfter(false);
        response.setOutputText(weatherInfo + "\n" + defaultView);
        output.setView(response);
    }

    private String colorRed(String text) {
        return ColoredStringBuilder.getColored(text, ColoredStringBuilder.AnsiColors.ANSI_RED.text());
    }
}
