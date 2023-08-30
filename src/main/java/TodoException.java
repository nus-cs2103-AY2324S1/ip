public class TodoException extends DukeException{

    public TodoException(String message) {
        super(Ui.connectTwoLine(message,
                "Enter in the form: \"todo [task_description]\""));
    }
}
