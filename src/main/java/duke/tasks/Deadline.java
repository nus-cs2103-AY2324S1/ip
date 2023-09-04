<<<<<<< HEAD
package duke.task;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
=======
package duke.tasks;

public class Deadline extends Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private static final String PRINT_FORMAT = "[D]%s %s (%s)";
    private String by;

    public Deadline(String info, String by) {
        super(info, TaskType.DEADLINE);
>>>>>>> branch-Level-7
        this.by = by;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")";
=======
        String status = this.isDone() ? DONE_FLAG : UNDONE_FLAG;
        return String.format(PRINT_FORMAT, status, this.getDescription(), by);
>>>>>>> branch-Level-7
    }
}

