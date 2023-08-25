package jeeves.exception;

public class DeletedIdException extends Exception {

    public DeletedIdException(String errorMsg) {
        super(errorMsg);
    }
}