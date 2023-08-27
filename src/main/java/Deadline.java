/**
 * Deadline task.
 */
public class Deadline extends Task{
    private String due;
    public Deadline(String taskContent, String due) {

        super(taskContent);
        this.due = due;
    }

    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [D] %s (by: %s)", statusAndTaskContent, due);
    }

    public static Deadline create(String status, String description, String due) {
        Deadline task = new Deadline(description, due);
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("D | %s | %s\n", super.saveToFileLine(), due);
    }
}
