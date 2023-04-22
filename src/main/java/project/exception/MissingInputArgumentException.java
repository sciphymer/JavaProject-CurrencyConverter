package project.exception;

public class MissingInputArgumentException extends RuntimeException{
    public MissingInputArgumentException(String message) {
        super(message);
    }
}
