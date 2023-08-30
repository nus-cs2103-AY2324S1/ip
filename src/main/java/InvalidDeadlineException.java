public class InvalidDeadlineException extends SaeException{

    @Override
    public String toString() {
        return "☹ OOPS!!! The deadline command should be followed by a description and /by.";
    }
}
