package homework14.exception;

public class IncorrectInputException extends Throwable {

    private String message;

    public IncorrectInputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
