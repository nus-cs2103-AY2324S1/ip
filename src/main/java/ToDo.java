public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    @Override
    public String getStatusMessage() {
        return "[T]" + super.getStatusMessage();
    }

    @Override
    public String toFormattedString() {
        return String.format("T | %s", super.toFormattedString());
    }
}
