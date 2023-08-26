public class TodoTask extends Task {
    public TodoTask(String description) throws IllegalArgumentException {
        super(description);
        if (description.length() == 0) {
            throw new IllegalArgumentException("Todo description cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "]" + this.description;
    }
}
