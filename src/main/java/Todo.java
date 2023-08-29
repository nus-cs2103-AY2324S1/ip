public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    @Override
    String getTaskType() {
        return "Todo";
    }

    @Override
    public String toString() {
        return "[T][" + (this.isDone() ? 'X' : ' ') + "] " + this.name;
    }
}
