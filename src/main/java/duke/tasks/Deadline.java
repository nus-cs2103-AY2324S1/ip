package duke.tasks;

public class Deadline extends Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private static final String PRINT_FORMAT = "[D]%s %s (%s)";
    private String by;

    public Deadline(String info, String by) {
        super(info, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = this.isDone() ? DONE_FLAG : UNDONE_FLAG;
        return String.format(PRINT_FORMAT, status, this.getDescription(), by);
    }
}

