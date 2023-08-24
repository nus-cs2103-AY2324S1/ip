public class ToDo extends Task {

    protected boolean isDone = false;
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}