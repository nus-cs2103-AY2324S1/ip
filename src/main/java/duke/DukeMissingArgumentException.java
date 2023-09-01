package duke;

public class DukeMissingArgumentException extends DukeException {
    @Override
    public String toString() {
        return String.format("Error: Command is missing argument(s)!");
    }

}
