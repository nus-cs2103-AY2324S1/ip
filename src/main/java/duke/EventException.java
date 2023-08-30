package duke;

public class EventException extends DukeException{

    public EventException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"event [task_description] /from {d/M/yyyy HH:mm} /to {d/M/yyyy HH:mm}\""));
    }
}
