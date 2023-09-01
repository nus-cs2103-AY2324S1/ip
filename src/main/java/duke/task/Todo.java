package duke.task;

public class Todo extends Task{
    public Todo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskFileString() {
        return "T" + " , " + super.getTaskFileString();
    }
}
