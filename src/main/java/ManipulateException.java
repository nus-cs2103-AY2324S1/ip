public class ManipulateException extends DukeException{

    public ManipulateException(String message, String command) {
        super(message + "Enter in the form: \"" + command + " [task_number]\" or \"" +
                command + " all\"");
    }
}
