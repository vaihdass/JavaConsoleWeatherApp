package ru.kpfu.itis.vaihdass.ConsoleImplementation;

import ru.kpfu.itis.vaihdass.Abstractions.Output;
import ru.kpfu.itis.vaihdass.DataStructures.Response;
import ru.kpfu.itis.vaihdass.Exceptions.OutputEmptyViewException;

import java.util.Scanner;

public class ConsoleOutput implements Output {
    @Override
    public void setView(Response response) {
        if (response == null) throw new OutputEmptyViewException("Response can't be null.");
        if (!response.isRequireLinebreakAfter()) {
            System.out.print(response.getOutputText());
        } else {
            System.out.println(response.getOutputText());
        }
    }
}
