<<<<<<< HEAD
package duke.task;

public class Event extends Task {
=======
package duke.tasks;

public class Event extends Task {
    private static final String DONE_FLAG = "[X] ";
    private static final String UNDONE_FLAG = "[ ] ";
    private static final String PRINT_FORMAT = "[E]%s %s (from: %s to: %s)";
>>>>>>> branch-Level-7
    private String from;
    private String to;

    public Event(String description, String from, String to) {
<<<<<<< HEAD
        super(description);
=======
        super(description, TaskType.EVENT);
>>>>>>> branch-Level-7
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
=======
        String status = this.isDone() ? DONE_FLAG : UNDONE_FLAG;
        return String.format(PRINT_FORMAT, status, this.getDescription(), from, to);
>>>>>>> branch-Level-7
    }
}


