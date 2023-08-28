package exception;

public class MissingTaskArgumentException extends MissingArgumentException {
    @Override
    public String toString() {
        return "  ☹ OOPS!!! You forgot to add the index number right after the command";
    }
}
