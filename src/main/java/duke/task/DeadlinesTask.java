package duke.task;

public class DeadlinesTask extends Task {
    private final String TYPE = "D";
    private final String deadline;

    public DeadlinesTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TYPE, super.toString(), this.deadline);
    }

    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1": "0";
        return String.format("%s,%s,%s,%s", TYPE, mark, this.name, this.deadline);
    }
}
