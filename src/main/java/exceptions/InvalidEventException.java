package exceptions;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("invalid deadline");
    }

    @Override
    public String toString() {
        return super.toString() + " Please enter a deadline with the '/from' and '/to' annotation";
    }
}
