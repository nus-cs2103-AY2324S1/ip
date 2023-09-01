package duke.task;

/**
 * To Do task.
 */
public class ToDo extends Task {
    public ToDo(String taskContent) {
        super(taskContent);
    }

    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [T] %s", statusAndTaskContent);
    }

    /**
     * Create To Do task.
     * @param status
     * @param description
     * @return
     */
    public static ToDo create(String status, String description) {
        ToDo task = new ToDo(description);
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("T | %s\n", super.saveToFileLine());
    }
}
