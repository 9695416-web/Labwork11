package exceptions;

public class NonZeroBalanceDeletionException extends AccountException {
    public NonZeroBalanceDeletionException(String message) {
        super(message);
    }
}