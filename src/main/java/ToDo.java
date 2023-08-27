public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean done, String description) {
        super(done, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String dataFormat() {
        return "todo/" +
                isDone.toString() + "/" +
                description;
    }
}
