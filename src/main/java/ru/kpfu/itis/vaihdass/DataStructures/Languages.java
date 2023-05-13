package ru.kpfu.itis.vaihdass.DataStructures;

public enum Languages {
    EN("en"),
    RU("ru");

    private final String locationCode;

    Languages(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationCode() {
        return locationCode;
    }
}
