package pl.radomczak.model.exception;

public class BuildNotFoundException extends Exception {
    public BuildNotFoundException(String message) {
        super(message);
    }
}