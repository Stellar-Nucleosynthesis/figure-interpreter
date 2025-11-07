package compile.exceptions;

public class VariableExistsException extends RuntimeException {
    public VariableExistsException(String message) {
        super(message);
    }
}