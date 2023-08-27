package Exception;

public class MissingTaskArgumentException extends MissingArgumentException {
    private String task;
    public MissingTaskArgumentException(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "  ☹ OOPS!!! The description of a " + this.task + " cannot be empty.";
    }
}
