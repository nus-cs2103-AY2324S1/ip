package taskmate.tools.tasks;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        return "[T][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name;
    }

    public String formatTaskForSaving() {
        return this.toString();
    }
}
