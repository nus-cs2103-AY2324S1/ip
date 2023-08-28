package glub.task;

public class ToDo extends Task {
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }
    @Override
    public String toSaveFormat() {
        return "T|" + super.toSaveFormat() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
