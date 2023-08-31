package kevin.exception;

public class KevinException extends Exception {
    public KevinException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
