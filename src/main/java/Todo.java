public class Todo extends Task {
    public Todo(String todoName) {
        super(todoName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String serialize() {
        return String.format("todo|%c|%s", (this.isDone() ? '1' : '0'), getTaskName());
    }
}
