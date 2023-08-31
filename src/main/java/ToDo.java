public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toMemoryFormat() {
        return String.format("T | %s | %s", (super.isDone ? "1" : "0"), super.taskName);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (super.isDone ? "X" : " "), super.taskName);
    }
}