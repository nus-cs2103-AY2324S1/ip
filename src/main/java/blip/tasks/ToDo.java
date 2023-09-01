package blip.tasks;

public class ToDo extends Task{

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveToFileString() {
        return "T " + (super.isDone ? "| 1 | " : "| 0 | ") + super.toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + super.toString();
    }
}
