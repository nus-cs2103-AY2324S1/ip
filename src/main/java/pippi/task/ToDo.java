package pippi.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toMemory() { return "T " + super.getStatus() + super.getDescription(); }
}
