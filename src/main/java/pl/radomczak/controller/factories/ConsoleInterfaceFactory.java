package pl.radomczak.controller.factories;

import pl.radomczak.controller.io.ConsoleUserInterface;
import pl.radomczak.controller.io.UserInterface;

public class ConsoleInterfaceFactory implements InterfaceFactory {
    @Override
    public UserInterface createInterface() {
        return new ConsoleUserInterface();
    }
}
