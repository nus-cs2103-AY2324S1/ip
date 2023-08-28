public class PrintDateException extends DukeException{

    public PrintDateException(String message) {
        super(message + "Enter in the form: \"print_date [deadline/event] /on {d/M/yyyy HH:mm}\"");
    }
}
