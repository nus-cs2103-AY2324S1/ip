public class ToDo extends Task {

    private ToDo(String taskName) throws IncompleteDescriptionException {
        super(taskName);
    }

    public static ToDo create(String taskName) throws IncompleteDescriptionException {
        return new ToDo(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
