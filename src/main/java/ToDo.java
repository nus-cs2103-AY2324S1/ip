public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getString() {
        return "[T]" + super.getString();
    }
}
