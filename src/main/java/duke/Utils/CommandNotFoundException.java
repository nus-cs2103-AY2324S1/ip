package duke.Utils;

public class CommandNotFoundException extends DukeException {
    protected CommandNotFoundException() {
        super("I'm sorry, but there is no such command.");
    }
}
