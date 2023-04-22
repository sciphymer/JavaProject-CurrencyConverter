package project.CurrencyConverter;

public class MissingInputArgumentException extends RuntimeException{
    public MissingInputArgumentException(String message) {
        super(message);
    }
}
