public class DukeInvalidTaskStringException extends DukeException {
    String taskNum;

    @Override
    public String toString() {
        return "Error: Unable to read task file!";
    }
}
