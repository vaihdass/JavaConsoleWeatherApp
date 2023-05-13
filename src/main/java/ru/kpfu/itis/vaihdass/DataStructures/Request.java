package ru.kpfu.itis.vaihdass.DataStructures;

public class Request {
    String command;

    public Request(String command) {
        if (command == null) throw new IllegalArgumentException("Command can't be null.");
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
