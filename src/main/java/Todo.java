public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String fileFormat() {
        return "T " + super.fileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}