package dot.errors;

public class DotException extends Exception {

    private final TaskError taskError;

    public DotException(String message, TaskError taskError) {
        super(message);
        this.taskError = taskError;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    public void handleError() {
        this.taskError.printErrorMessage(this);
    }

}

