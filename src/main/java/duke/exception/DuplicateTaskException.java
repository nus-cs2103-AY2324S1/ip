package duke.exception;

public class DuplicateTaskException extends DukeException{
    public DuplicateTaskException() {
        super("☹ OOPS!!! I'm sorry, but this task already exists");
    }
}
