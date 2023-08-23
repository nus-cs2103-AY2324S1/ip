public class EmptyTaskException extends Exception{
    public EmptyTaskException(String type, String field) {
        super("☹ OOPS!!! The " + field + " of a " + type + " cannot be empty.");
    }
}
