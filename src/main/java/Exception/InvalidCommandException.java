package Exception;

public class InvalidCommandException extends Exception {
    @Override
    public String toString() {
        return "This is not a registered command that I know of";
    }
}
