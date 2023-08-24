public class OutOfBoundsException extends DukeException {
    public OutOfBoundsException(int index, String taskCount) {
        super(String.format("☹ OOPS!!! %d is out of range. %s", index, taskCount));
    }
}