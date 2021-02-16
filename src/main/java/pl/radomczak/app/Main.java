package pl.radomczak.app;

import pl.radomczak.controller.factories.ConsoleInterfaceFactory;
import pl.radomczak.controller.factories.InterfaceFactory;
import pl.radomczak.controller.io.UserInterface;
import pl.radomczak.controller.WheelControl;

public class Main
{
    public static void main(String[] args)
    {
        WheelControl wheelControl = new WheelControl();
        InterfaceFactory factory = new ConsoleInterfaceFactory();
        UserInterface userInterface = factory.createInterface();
        userInterface.applyFor(wheelControl);
        userInterface.handle();
    }
}
