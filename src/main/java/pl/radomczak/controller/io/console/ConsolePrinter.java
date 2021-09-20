package pl.radomczak.controller.io.console;

import pl.radomczak.model.Option;

public class ConsolePrinter implements Printer {

    public <T> void printArray(T[] tArray) {
        for (T t : tArray)
        {
            print(t+", ");
        }
        System.out.println();
    }

    public void printOptions() {
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }
    public <T> void print(T t)
    {
        System.out.println(t);
    }
}
