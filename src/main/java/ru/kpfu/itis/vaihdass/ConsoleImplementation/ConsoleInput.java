package ru.kpfu.itis.vaihdass.ConsoleImplementation;

import ru.kpfu.itis.vaihdass.Abstractions.Controller;
import ru.kpfu.itis.vaihdass.Abstractions.Input;
import ru.kpfu.itis.vaihdass.Abstractions.Output;
import ru.kpfu.itis.vaihdass.DataStructures.Response;
import ru.kpfu.itis.vaihdass.Exceptions.OutputEmptyViewException;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleInput extends Input {
    private Scanner scanner;

    public ConsoleInput(InputStream inputStream, Controller controller) {
        super(controller);
        scanner = new Scanner(inputStream);
    }

    public void requestCommands(String greetingCommand) {
        if (greetingCommand == null) throw new IllegalArgumentException("Greeting message can't be null.");
        setLastCommand(greetingCommand);
        lastCommandChanged();

        String command;
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            if (command != null) {
                setLastCommand(command);
                lastCommandChanged();
            }
        }
    }

    public String requestNewCommand() {
        String command;
        try {
            if (scanner.hasNextLine()) {
                command = scanner.nextLine();
                if (command != null) {
                    setLastCommand(command);
                    return command;
                }
            }
        } catch (IllegalStateException e) {
            return null;
        }
        return null;
    }
}
