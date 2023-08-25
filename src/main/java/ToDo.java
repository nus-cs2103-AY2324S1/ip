public class ToDo extends Task {

    public ToDo(String description) throws SpotException {
        super(description);
    }

    public ToDo(String description, boolean isDone) throws SpotException {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toLine() {
        return " T | " + super.toLine();
    }
}
