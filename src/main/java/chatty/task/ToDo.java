package chatty.task;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[T][X] " + this.task;
        } else {
            return "[T][ ] " + this.task;
        }
    }
}
