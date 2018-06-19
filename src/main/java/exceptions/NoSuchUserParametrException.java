package exceptions;

public class NoSuchUserParametrException extends RuntimeException {
    public NoSuchUserParametrException(String message) {
        super(message);
    }
}
