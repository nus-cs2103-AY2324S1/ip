package pooh;

public class InvalidTaskException extends PoohException {
    @Override
    public String toString() {
        return "      ☹ OOPS!!! No such task! Please reselect carefully!";
    }
}
