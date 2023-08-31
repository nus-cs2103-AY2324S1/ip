public class Event extends Task {
    String startDate;
    String dueDate;
    public Event(String taskName, String startDate,String dueDate) {
        super(taskName, TaskType.EVENT);
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startDate + " to: " + dueDate + ")";
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
