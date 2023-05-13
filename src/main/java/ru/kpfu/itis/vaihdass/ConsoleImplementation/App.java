package ru.kpfu.itis.vaihdass.ConsoleImplementation;

import ru.kpfu.itis.vaihdass.Abstractions.iApp;
import ru.kpfu.itis.vaihdass.Helpers.ColoredStringBuilder;

public class App implements iApp {
    private ConsoleInput input;

    public static void main(String[] args) {
        iApp app = new App();
        app.init();
        app.start();
    }

    @Override
    public void init() {
        this.input = new ConsoleInput(System.in, new DefaultController(new ConsoleOutput(), ColoredStringBuilder.getColored(">>> ", ColoredStringBuilder.AnsiColors.ANSI_CYAN.bold()),
                ColoredStringBuilder.getColored("(Консольное приложение получения погоды через API OpenMeteo)\n", ColoredStringBuilder.AnsiColors.ANSI_BLUE.text()) +
                        ColoredStringBuilder.getColored("Чтобы получить информацию о погоде, напишите команду вида \"Город - Период\".\n", ColoredStringBuilder.AnsiColors.ANSI_YELLOW.bold()) +
                        ColoredStringBuilder.getColored("(Период может быть: сегодня, завтра, на неделю)", ColoredStringBuilder.AnsiColors.ANSI_BLUE.text())));
    }

    @Override
    public void start() {
        input.requestCommands("Помощь");
    }
}
