public class MissingTaskException extends DukeException{
    MissingTaskException(String command) {
        super(command + " needs a task after it...");
    }
}