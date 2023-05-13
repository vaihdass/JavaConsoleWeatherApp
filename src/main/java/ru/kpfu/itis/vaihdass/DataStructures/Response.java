package ru.kpfu.itis.vaihdass.DataStructures;

public class Response {
    String outputText;
    private boolean requireLinebreakAfter = true;

    public Response() {}

    public void setRequireLinebreakAfter(boolean state) {
        requireLinebreakAfter = state;
    }

    public boolean isRequireLinebreakAfter() {
        return requireLinebreakAfter;
    }

    public String getOutputText() {
        return outputText;
    }

    public void setOutputText(String outputText) {
        this.outputText = outputText;
    }
}
