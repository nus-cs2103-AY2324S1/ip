package duke;

public class DukeMissingTaskException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " No such task exists.";
    }
}
