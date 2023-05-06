package az.turbo.main;

public class InternetConnectionException extends RuntimeException {
    public InternetConnectionException(String message, Exception e) {
        super(message, e);
    }
}
