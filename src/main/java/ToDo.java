public class ToDo extends Task {

    private ToDo(String taskName) throws IncompleteDescriptionException {
        super(taskName);
    }

    public static ToDo create(String taskName) throws IncompleteDescriptionException {
        return new ToDo(taskName);
    }

    @Override
    public String compressData() {
        char isDoneChar = this.isDone ? '1' : '0';
        return "T" + " | " + isDoneChar + " | " + this.taskName;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
