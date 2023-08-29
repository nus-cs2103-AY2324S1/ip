package services.tasklist.tasks;

public class Deadline extends Task {
    private String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String encode() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime + ")";
    }
}
