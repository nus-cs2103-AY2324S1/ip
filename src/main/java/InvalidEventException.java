public class InvalidEventException extends SaeException {

    @Override
    public String toString() {
        return "☹ OOPS!!! The event command should be followed by a description, /from, and /to.";
    }

}
