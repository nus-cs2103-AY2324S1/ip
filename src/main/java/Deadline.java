public class Deadline extends Task {
    String dueDate;
    public Deadline(String taskName, String dueDate) {
        super(taskName, TaskType.DEADLIINE);
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
            return "[D]"+ super.toString() + " (by: " + dueDate + ")";
    }

    // mark task as done and print out the line
    @Override
    public void markDone() {
        isDone = true;
    }

    // mark task as undone and print out the line
    @Override
    public void unmarkDone() {
        isDone = false;
    }
}
