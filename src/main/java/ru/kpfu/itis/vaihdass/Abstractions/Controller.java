package ru.kpfu.itis.vaihdass.Abstractions;

import ru.kpfu.itis.vaihdass.DataStructures.Request;
import ru.kpfu.itis.vaihdass.DataStructures.Response;

public interface Controller {
    void getUpdatedView(Request request);
}
