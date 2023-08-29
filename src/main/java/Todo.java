public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    Todo(String name, boolean isDone) {
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

    String formatTaskForSaving() {
        return this.toString();
    }
}
