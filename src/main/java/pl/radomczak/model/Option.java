package pl.radomczak.model;

import pl.radomczak.model.exception.NoSuchOptionException;

public enum Option {
    EXIT(0,"Wyjście z programu"),
    ADDSKILL(1,"Dodaj zdolność"),
    PRINTSKILLS(2,"Wyświetl zdolności"),
    ADDABILITY(3,"Dodaj umiejętność"),
    PRINTABILITIES(4,"Wyświetl umiejętności"),
    ADDHERO(5,"Dodaj bohatera"),
    PRINTHEROES(6,"Wyświetl bohaterów");

    private final int value;
    private final String desc;

    Option(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return value + " - " + desc;
    }

    public static Option createOptionFromInt(int option) throws NoSuchOptionException {
        try {
            return Option.values()[option];
        }catch (IndexOutOfBoundsException e) {
            throw new NoSuchOptionException("Nie ma takiej opcji o id " + option);
        }
    }
}
