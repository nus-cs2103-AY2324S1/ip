public class MissingDateException extends DukeException {

    public MissingDateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Date of the task is missing! Please give your task a date :)";
    }

}

