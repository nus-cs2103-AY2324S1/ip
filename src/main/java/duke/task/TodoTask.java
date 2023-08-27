package duke.task;

public class TodoTask extends Task {
    private final String TYPE = "T";

    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TYPE, super.toString());
    }

    @Override
    public String toStringStore() {
        String mark = this.isDone ? "1": "0";
        return String.format("%s,%s,%s", TYPE, mark, this.name);
    }
}
