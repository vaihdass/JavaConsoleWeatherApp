package ru.kpfu.itis.vaihdass.Abstractions;

import ru.kpfu.itis.vaihdass.DataStructures.Request;

public abstract class Input {
    private Controller controller;
    private String lastCommand;

    public Input(Controller controller) {
        setController(controller);
    }

    public void setController(Controller controller) {
        if (controller == null) throw new IllegalArgumentException("Controller can't be null.");
        this.controller = controller;
    }

    public void lastCommandChanged() {
        controller.getUpdatedView(new Request(lastCommand));
    }

    public String getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(String lastCommand) {
        if (lastCommand == null) throw new IllegalArgumentException("Command can't be null.");
        this.lastCommand = lastCommand;
    }
}
