package qi.task;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.task;
        }
        return "[T][ ] " + this.task;
    }
}
