package figures;

public class InvalidFigureException extends RuntimeException {
    public InvalidFigureException(String message) {
        super(message);
    }
}