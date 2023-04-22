package project.CurrencyConverter;

public class CurrencyRateNotFoundException extends RuntimeException{
    public CurrencyRateNotFoundException(String message) {
        super(message);
    }
}
