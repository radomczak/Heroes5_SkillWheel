package pl.radomczak.controller.io;

import pl.radomczak.controller.WheelControl;

public interface UserInterface
{
    void handle();
    void close();
    void applyFor(WheelControl wheelControl);
}
